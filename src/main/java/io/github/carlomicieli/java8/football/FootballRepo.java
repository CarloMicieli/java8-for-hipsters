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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It represents a stadiums/teams repository.
 *
 * @author Carlo Micieli
 */
public final class FootballRepo {

    private final static Set<Stadium> _stadiums = Stadiums.stream().collect(Collectors.toSet());
    private final static Set<Team> _teams = Teams.initTeams();

    public static Stream<Stadium> stadiums() {
        return _stadiums.stream();
    }

    public static Stream<Team> teams() {
        return _teams.stream();
    }
}