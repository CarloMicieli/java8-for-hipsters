/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.carlomicieli.java8.web;

import io.github.carlomicieli.java8.football.Stadium;
import io.github.carlomicieli.java8.football.Stadiums;
import io.github.carlomicieli.java8.football.Team;
import io.github.carlomicieli.java8.football.Teams;
import spark.*;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.github.carlomicieli.java8.web.JsonTransformerRoute.newRoute;
import static io.github.carlomicieli.java8.web.StreamsHelper.paginate;

/**
 * @author Carlo Micieli
 */
public class SparkApplication {

    public static void start() {
        get("/", (req, resp) -> "Welcome to #Java8");
        get("/teams", (req, resp) -> paginate(Teams.stream(), req).collect(Collectors.toList())
        );
        get("/teams/:id", (req, resp) -> {
            int teamId = Integer.parseInt(req.params(":id"));
            Supplier<Team> notFound = () -> {
                resp.status(404);
                return null;
            };
            return Teams.findById(teamId).orElseGet(notFound);
        });
        get("/stadiums", (req, resp) -> paginate(Stadiums.stream(), req).collect(Collectors.toList())
        );
        get("/stadiums/:id", (req, resp) -> {
            int stadiumId = Integer.parseInt(req.params(":id"));
            Supplier<Stadium> notFound = () -> {
                resp.status(404);
                return null;
            };
            return Stadiums.findById(stadiumId).orElseGet(notFound);
        });
    }

    private static void get(String path, BiFunction<Request, Response, Object> handler) {
        Spark.get(newRoute(path, handler));
    }

}
