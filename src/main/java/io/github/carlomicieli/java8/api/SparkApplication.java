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
package io.github.carlomicieli.java8.api;

import io.github.carlomicieli.java8.football.Stadium;
import io.github.carlomicieli.java8.football.Stadiums;
import io.github.carlomicieli.java8.football.Team;
import io.github.carlomicieli.java8.football.Teams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;

import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Carlo Micieli
 */
public class SparkApplication {

    private final static Logger log = LoggerFactory.getLogger(SparkApplication.class);

    public static void start() {
        get("/", (req, resp) -> "Welcome to #Java8");
        get("/teams", (req, resp) -> {
            Paginate paging = Paginate.of(req);
            return Teams.stream()
                    .skip(paging.skip)
                    .limit(paging.limit)
                    .collect(Collectors.toList());
        });
        get("/teams/:id", (req, resp) -> {
            int teamId = Integer.parseInt(req.params(":id"));
            Supplier<Team> notFound = () -> { resp.status(404); return null; };
            return Teams.findById(teamId).orElseGet(notFound);
        });
        get("/stadiums", (req, resp) -> {
            Paginate paging = Paginate.of(req);
            return Stadiums.stream()
                    .skip(paging.skip)
                    .limit(paging.limit)
                    .collect(Collectors.toList());
        });
        get("/stadiums/:id", (req, resp) -> {
            int stadiumId = Integer.parseInt(req.params(":id"));
            Supplier<Stadium> notFound = () -> { resp.status(404); return null; };
            return Stadiums.findById(stadiumId).orElseGet(notFound);
        });
    }

    private static void get(String path, RequestHandler handler) {
        Spark.get(new JsonTransformerRoute(path) {
            @Override
            public Object handle(Request request, Response response) {
                log.info("[{}]: request received!", path);
                response.type("application/json");
                return handler.apply(request, response);
            }
        });
    }

    @FunctionalInterface
    static interface RequestHandler {
        Object apply(Request request, Response response);
    }

    static class Paginate {
        final int skip;
        final int limit;

        private Paginate(int page, int pageSize) {
            skip = (page - 1) * pageSize;
            limit = pageSize;
        }

        static Paginate of(Request req) {
            int page = param(req, "page", 1);
            int size = param(req, "size", 10);
            return new Paginate(page, size);
        }

        private static int param(Request req, String name, int defaultValue) {
            String val = req.queryParams(name);
            if (val == null || val.equals("")) return defaultValue;
            return Integer.parseInt(val);
        }
    }
}
