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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * @author Carlo Micieli
 */
public class FootballRepoTests {

    @Test
    public void shouldProduceTheTeamsList() {
        Stream<Team> teams = teams();
        assertThat(teams.count(), is(equalTo(32L)));
    }

    @Test
    public void shouldProduceTheStadiumsList() {
        Stream<Stadium> stadiums = stadiums();
        assertThat(stadiums.count(), is(equalTo(31L)));
    }

    @Test
    public void shouldReturnTheFirstTwoTeamsSortedByName() {
        List<Team> teams = teams().sorted().limit(2).collect(Collectors.toList());
        assertThat(teams.size(), is(equalTo(2)));
        assertThat(teams.get(0).getName(), is(equalTo("Arizona Cardinals")));
        assertThat(teams.get(1).getName(), is(equalTo("Atlanta Falcons")));
    }

    @Test
    public void shouldReturnTheNamesListForNFCWest() {
        String teamsList = teams()
                .filter(t -> t.getConference().equals("NFC") && t.getDivision().equals("West"))
                .sorted()
                .map(Team::getName)
                .collect(Collectors.joining(", "));
        assertThat(teamsList, is(equalTo("Arizona Cardinals, San Francisco 49ers, Seattle Seahawks, St. Louis Rams")));
    }

    @Test
    public void shouldPartitionTeamsByConference() {
        Map<Boolean, List<Team>> nfcPart = teams()
                .collect(Collectors.partitioningBy(t -> t.getConference().equals("NFC")));
        assertThat(nfcPart.size(), is(equalTo(2)));
        assertThat(nfcPart.get(true).size(), is(equalTo(16)));
        assertThat(nfcPart.get(false).size(), is(equalTo(16)));
    }

    @Test
    public void shouldReturnTheFirstTwoStadiumByOpenedYear() {
        List<Stadium> oldest = stadiums()
                .sorted((x, y) -> Integer.compare(x.getOpenedYear(), y.getOpenedYear()))
                .limit(2)
                .collect(Collectors.toList());
        assertThat(oldest.size(), is(equalTo(2)));
        assertThat(oldest.get(0).getName(), is(equalTo("Soldier Field")));
        assertThat(oldest.get(0).getOpenedYear(), is(equalTo(1924)));
        assertThat(oldest.get(1).getName(), is(equalTo("Lambeau Field")));
        assertThat(oldest.get(1).getOpenedYear(), is(equalTo(1957)));
    }

    @Test
    public void shouldGetTheStadiumWithTheMaxCapacity() {
        Optional<Stadium> biggest = stadiums().max(Stadium::compareByCapacity);
        assertThat(biggest.isPresent(), is(true));
        assertThat(biggest.get().getCapacity(), is(equalTo(105_121)));
    }

    @Test
    public void shouldGetTheAverageCapacityForStadiums() {
        double average = stadiums()
                .mapToInt(Stadium::getCapacity)
                .average()
                .getAsDouble();
        assertThat(average, is(closeTo(73484.32, 0.01)));
    }

    @Test
    public void shouldReturnTheOpenedStadiumsWithGrassField() {
        List<Stadium> openAndGrassStadiums = stadiums()
                .filter(s -> s.getRoofType() == RoofType.OPEN)
                .filter(s -> s.getSurface() == PlayingSurface.GRASS)
                .distinct()
                .collect(Collectors.toList());

        assertThat(openAndGrassStadiums, is(notNullValue()));
        assertThat(openAndGrassStadiums.size(), is(equalTo(7)));
    }

    @Test
    public void shouldReturnTheTotalCapacityForAllTheStadiums() {
        long totalCapacity = stadiums()
                .mapToLong(Stadium::getCapacity)
                .sum();
        assertThat(totalCapacity, is(equalTo(2_278_014L)));
    }

    @Test
    public void shouldReturnTheNamesListForTheDomedStadiums() {
        String stadiumsStr = stadiums()
                .filter(s -> s.getRoofType() == RoofType.DOMED)
                .map(Stadium::getName)
                .sorted()
                .reduce("", (acc, name) -> acc + name + ", ");
        assertThat(stadiumsStr, is(equalTo("Edward Jones Dome, Ford Field, Georgia Dome, Mercedes-Benz Superdome, ")));
    }

    private Stream<Stadium> stadiums() {
        return FootballRepo.stadiums();
    }

    private Stream<Team> teams() {
        return FootballRepo.teams();
    }

}