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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class FootballServiceTests {

    private FootballService service = new FootballService();

    @Test
    public void shouldReturnEmptyValueIfTeamIsNotFound() {
        Optional<Team> empty = service.findTeam("Not found");
        assertThat(empty.isPresent(), is(false));
    }

    @Test
    public void shouldFindATeam() {
        Optional<Team> sf = service.findTeam("San Francisco 49ers");
        assertThat(sf.isPresent(), is(true));
        assertThat(sf.get().getName(), is(equalTo("San Francisco 49ers")));
    }

    @Test
    public void shouldReturnEmptyValueIfStadiumIsNotFound() {
        Optional<Stadium> empty = service.findStadium("Not found");
        assertThat(empty.isPresent(), is(false));
    }

    @Test
    public void shouldFindAStadium() {
        Optional<Stadium> sf = service.findStadium("FedEx Field");
        assertThat(sf.isPresent(), is(true));
        assertThat(sf.get().getName(), is(equalTo("FedEx Field")));
    }

    @Test
    public void shouldGroupTeamsByTheirDivisions() {
        Map<String, List<Team>> divisions = service.teamsByDivision();
        assertThat(divisions.get("NFC West"), is(notNullValue()));
        assertThat(divisions.get("NFC West").size(), is(equalTo(4)));
    }

}
