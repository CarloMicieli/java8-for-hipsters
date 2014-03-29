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

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class TeamsTests {
    @Test
    public void shouldLoadTeamsData() {
        long count = Teams.stream().count();
        assertThat(count, is(equalTo(32L)));
    }

    @Test
    public void shouldGetTeamsList() {
        int count = Teams.list().size();
        assertThat(count, is(equalTo(32)));
    }

    @Test
    public void shouldReturnEmptyValueIfTeamIsNotFound() {
        Optional<Team> empty = Teams.findByName("Not found");
        assertThat(empty.isPresent(), is(false));
    }

    @Test
    public void shouldGroupTeamsByTheirDivisions() {
        Map<String, List<Team>> divisions = Teams.teamsByDivision();
        assertThat(divisions.get("NFC West"), is(notNullValue()));
        assertThat(divisions.get("NFC West").size(), is(equalTo(4)));
    }

    @Test
    public void shouldFindTeamsById() {
        Optional<Team> sf = Teams.findById(4);
        assertThat(sf.isPresent(), is(true));
        assertThat(sf.get().getName(), is(equalTo("San Francisco 49ers")));
    }

    @Test
    public void shouldReturnTheFirstTwoTeamsSortedByName() {
        List<Team> teams = Teams.stream()
                .sorted()
                .limit(2)
                .collect(toList());

        assertThat(teams.size(), is(equalTo(2)));
        assertThat(teams.get(0).getName(), is(equalTo("Arizona Cardinals")));
        assertThat(teams.get(1).getName(), is(equalTo("Atlanta Falcons")));
    }

    @Test
    public void shouldReturnTeamNamesListByDivision() {
        Map<String, List<String>> divisions = Teams.stream()
                .sorted()
                .collect(groupingBy(Team::getFullDivisionName,
                        mapping(Team::getName, toList())));

        assertThat(divisions.size(), is(CoreMatchers.equalTo(8)));
        assertThat(divisions.get("NFC West"),
                contains("Arizona Cardinals", "San Francisco 49ers", "Seattle Seahawks", "St. Louis Rams"));
        assertThat(divisions.get("NFC East"),
                contains("Dallas Cowboys", "New York Giants", "Philadelphia Eagles", "Washington Redskins"));
    }

    @Test
    public void shouldConcatenateTheTeamNamesForNFCWest() {
        String teamsList = Teams.stream()
                .filter(t -> t.getConference().equals("NFC") && t.getDivision().equals("West"))
                .sorted()
                .map(Team::getName)
                .collect(Collectors.joining(", "));

        assertThat(teamsList, is(equalTo("Arizona Cardinals, San Francisco 49ers, Seattle Seahawks, St. Louis Rams")));
    }

    @Test
    public void shouldPartitionTeamsByConference() {
        Map<Boolean, List<Team>> nfcPart = Teams.stream()
                .collect(Collectors.partitioningBy(t -> t.getConference().equals("NFC")));

        assertThat(nfcPart.size(), is(equalTo(2)));
        assertThat(nfcPart.get(true).size(), is(equalTo(16)));
        assertThat(nfcPart.get(false).size(), is(equalTo(16)));
    }
}
