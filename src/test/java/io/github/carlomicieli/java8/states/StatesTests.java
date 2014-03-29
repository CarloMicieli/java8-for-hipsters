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
package io.github.carlomicieli.java8.states;

import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.carlomicieli.java8.states.StateTestsHelper.california;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StatesTests {

    @Test
    public void shouldLoadStatesList() {
        assertThat(States.stream().count(), is(equalTo(50L)));
    }

    @Test
    public void shouldGetTheLastFiveStatesNamesInAlphabeticOrder() {
        List<String> names = States.stream()
                .map(State::getName)
                .sorted((a, b) -> b.compareTo(a))
                .limit(5)
                .collect(Collectors.toList());
        assertThat(names.size(), is(equalTo(5)));
        assertThat(names,
                contains("Wyoming", "Wisconsin", "West Virginia", "Washington", "Virginia"));
    }

    @Test
    public void shouldGetTheFiveStatesWithTheBiggestPopulation() {
        List<String> names = States.stream()
                .sorted((a, b) -> Long.compare(b.getPopulation(), a.getPopulation()))
                .limit(5)
                .map(State::getName)
                .collect(Collectors.toList());
        assertThat(names,
                contains("California", "Texas", "New York", "Florida", "Illinois"));
    }

    @Test
    public void shouldGetAveragePopulationForTheFiveBiggestStates() {
        Comparator<State> byPopulationDesc =
                (a, b) -> Long.compare(b.getPopulation(), a.getPopulation());

        OptionalDouble avg = States.stream()
                .sorted(byPopulationDesc)
                .mapToLong(State::getPopulation)
                .average();
        assertThat(avg.isPresent(), is(true));
        assertThat(avg.getAsDouble(), is(closeTo(6_309_204.1, 0.1)));
    }

    @Test
    public void shouldGetTheStatesToJoinTheUnionBefore1800() {
        final String[] EXPECTED_STATES_NAME = {
            "Delaware", "Pennsylvania", "New Jersey", "Georgia", "Connecticut"
        };
        final LocalDate year1800 = LocalDate.of(1800, 1, 1);

        String[] names = States.stream()
                .filter(s -> s.getStatehood().isBefore(year1800))
                .sorted((a, b) -> a.getStatehood().compareTo(b.getStatehood()))
                .limit(5)
                .map(State::getName)
                .toArray(String[]::new);

        assertThat(names.length, is(equalTo(5)));
        assertThat(names, is(equalTo(EXPECTED_STATES_NAME)));
    }

    @Test
    public void shouldGetTheStatesWithTotalAreaBiggerThan500_000KM() {
        long count = States.stream()
                .filter(s -> s.getArea() > 500_000)
                .count();
        assertThat(count, is(equalTo(2L)));
    }

    @Test
    public void shouldGetLastThreeStatesToJoinTheUnion() {
        List<String> names = States.stream()
                .sorted((a, b) -> b.getStatehood().compareTo(a.getStatehood()))
                .limit(3)
                .map(State::getAbbreviation)
                .collect(Collectors.toList());

        Collections.sort(names, (a, b) -> a.compareTo(b));
        assertThat(names, contains("AZ", "HI", "NM"));
    }

    @Test
    public void shouldGetPopulationDensityForStates() {
        Function<State, Long> calculateDensity = (State s) -> s.getPopulation() / s.getArea();

        Map<State, Long> density = States.stream()
                .collect(Collectors.toMap(Function.identity(), calculateDensity));

        assertThat(density, hasEntry(california(), 90L));
    }

}
