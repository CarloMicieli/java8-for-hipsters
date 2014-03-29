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
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StadiumsTests {
    @Test
    public void shouldLoadTheStadiumsData() {
        long count = Stadiums.stream().count();
        assertThat(count, is(equalTo(31L)));
    }

    @Test
    public void shouldLoadTheStadiumForSanFrancisco49ers() {
        Stadium levis = Stadiums.stream()
                .filter(s -> s.getTeamNames().contains("San Francisco 49ers"))
                .findFirst()
                .orElse(null);

        assertThat(levis, is(notNullValue()));
        assertThat(levis.getName(), is(equalTo("Levis Stadium")));
        assertThat(levis.getRoofType(), is(equalTo(RoofType.OPEN)));
        assertThat(levis.getSurface(), is(equalTo(PlayingSurface.BERMUDA_GRASS)));
    }

    @Test
    public void shouldReturnEmptyValueIfStadiumIsNotFound() {
        Optional<Stadium> empty = Stadiums.findByName("Not found");
        assertThat(empty.isPresent(), is(false));
    }

    @Test
    public void shouldFindAStadiumByName() {
        Optional<Stadium> sf = Stadiums.findByName("FedEx Field");
        assertThat(sf.isPresent(), is(true));
        assertThat(sf.get().getName(), is(equalTo("FedEx Field")));
    }

    @Test
    public void shouldFindAStadiumById() {
        Optional<Stadium> sf = Stadiums.findById(1);
        assertThat(sf.isPresent(), is(true));
        assertThat(sf.get().getName(), is(equalTo("Levis Stadium")));
    }

    @Test
    public void shouldReturnTheFirstTwoStadiumByOpenedYear() {
        List<Stadium> oldest = Stadiums.stream()
                .sorted((x, y) -> Integer.compare(x.getOpenedYear(), y.getOpenedYear()))
                .limit(2)
                .collect(toList());

        assertThat(oldest.size(), is(CoreMatchers.equalTo(2)));
        assertThat(oldest.get(0).getName(), is(equalTo("Soldier Field")));
        assertThat(oldest.get(0).getOpenedYear(), is(equalTo(1924)));
        assertThat(oldest.get(1).getName(), is(equalTo("Lambeau Field")));
        assertThat(oldest.get(1).getOpenedYear(), is(equalTo(1957)));
    }

    @Test
    public void shouldGetTheStadiumWithTheMaxCapacity() {
        Optional<Stadium> biggest = Stadiums.stream().max(Stadium::compareByCapacity);
        assertThat(biggest.isPresent(), is(true));
        assertThat(biggest.get().getCapacity(), is(equalTo(105_121)));
    }

    @Test
    public void shouldGetTheAverageCapacityForStadiums() {
        double average = Stadiums.stream()
                .mapToInt(Stadium::getCapacity)
                .average()
                .getAsDouble();
        assertThat(average, is(closeTo(73484.32, 0.01)));
    }

    @Test
    public void shouldReturnTheOpenedStadiumsWithGrassField() {
        List<Stadium> openAndGrassStadiums = Stadiums.stream()
                .filter(s -> s.getRoofType() == RoofType.OPEN)
                .filter(s -> s.getSurface() == PlayingSurface.GRASS)
                .distinct()
                .collect(toList());

        assertThat(openAndGrassStadiums, is(notNullValue()));
        assertThat(openAndGrassStadiums.size(), is(equalTo(7)));
    }

    @Test
    public void shouldReturnTheTotalCapacityForAllTheStadiums() {
        long totalCapacity = Stadiums.stream()
                .mapToLong(Stadium::getCapacity)
                .sum();
        assertThat(totalCapacity, is(equalTo(2_278_014L)));
    }

    @Test
    public void shouldCalculateTotalCapacityForStadiumsInCalifornia() {
        Predicate<Stadium> inCalifornia = s -> s.getState().equals("California");
        int total = Stadiums.stream()
                .filter(inCalifornia)
                .mapToInt(Stadium::getCapacity)
                .reduce(0, Math::addExact);
        assertThat(total, is(equalTo(209_761)));
    }

    @Test
    public void shouldReturnTheNamesListForTheDomedStadiums() {
        String stadiumsStr = Stadiums.stream()
                .filter(s -> s.getRoofType() == RoofType.DOMED)
                .map(Stadium::getName)
                .sorted()
                .reduce("", (acc, name) -> acc + name + ", ");
        assertThat(stadiumsStr, is(equalTo("Edward Jones Dome, Ford Field, Georgia Dome, Mercedes-Benz Superdome, ")));
    }

}
