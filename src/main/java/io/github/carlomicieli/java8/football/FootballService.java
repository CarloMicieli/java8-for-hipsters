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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.github.carlomicieli.java8.football.FootballRepo.stadiums;
import static io.github.carlomicieli.java8.football.FootballRepo.teams;

/**
 * @author Carlo Micieli
 */
public class FootballService {

    public Optional<Team> findTeam(String name) {
        return teams()
                .filter(t -> t.getName().equals(name))
                .findFirst();
    }

    public Optional<Stadium> findStadium(String stadiumName) {
        return stadiums()
                .filter(s -> s.getName().equals(stadiumName))
                .findFirst();
    }

    public Map<String, List<Team>> teamsByDivision() {
        return teams().collect(Collectors.groupingBy(Team::getFullDivisionName));
    }

}
