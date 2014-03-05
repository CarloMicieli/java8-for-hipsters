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

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Carlo Micieli
 */
public class FootballRepoTests {

    @Test
    public void shouldProduceTheTeamsList() {
        Set<Team> teams = FootballRepo.teams();
        assertThat(teams, hasSize(32));
    }

    @Test
    public void shouldProduceTheStadiumsList() {
        Set<Stadium> stadiums = FootballRepo.stadiums();
        assertThat(stadiums, hasSize(31));
    }
}