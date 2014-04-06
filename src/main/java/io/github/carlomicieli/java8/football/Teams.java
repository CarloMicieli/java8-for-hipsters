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
package io.github.carlomicieli.java8.football;

import com.google.gson.reflect.TypeToken;
import io.github.carlomicieli.java8.helpers.Loader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Carlo Micieli
 */
public class Teams {

    private static final String TEAMS_DATA_JSON = "/data/teams.json";
    private static final List<Team> data = teamsList();

    public static Stream<Team> stream() {
        return data.stream();
    }

    public static List<Team> list() {
        return data;
    }

    public static Optional<Team> findByName(String teamName) {
        return stream()
                .filter(t -> t.getName().equals(teamName))
                .findFirst();
    }

    public static Map<String, List<Team>> teamsByDivision() {
        return stream().collect(Collectors.groupingBy(Team::getFullDivisionName));
    }

    private static List<Team> teamsList() {
        Loader l = new Loader(TEAMS_DATA_JSON);
        return l.load(new TypeToken<List<Team>>() {}.getType());
    }

    public static Optional<Team> findById(int id) {
        return stream().filter(t -> t.getId() == id).findFirst();
    }
}

