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
package io.github.carlomicieli.java8.cities;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class CitiesTests {
    @Test
    public void shouldLoadCities() {
        assertThat(Cities.stream().count(), is(equalTo(809L)));
    }

    @Test
    public void shouldGetBiggestCity() {
        City biggest = Cities.stream()
                .sorted(Comparator.comparing(City::getPopulation).reversed())
                .findFirst().orElse(null);
        assertThat(biggest.getName(), is(equalTo("Yokohama")));
        assertThat(biggest.getPopulation(), is(equalTo(3_631_236)));
    }

    @Test
    public void shouldGetTheNumberOfPrefectures() {
        long count = Cities.stream()
                .map(City::getPrefecture)
                .distinct()
                .count();
        assertThat(count, is(equalTo(47L)));
    }

    @Test
    public void shouldCreatePartitionsByCityArea() {
        Map<Boolean, List<City>> partitions = Cities.stream()
                .collect(Collectors.partitioningBy(c -> c.getArea() > 500.0));

        assertThat(partitions.size(), is(equalTo(2)));
        assertThat(partitions.get(true), hasSize(137));
        assertThat(partitions.get(false), hasSize(672));
    }

    @Test
    public void shouldGetTheCityNamesForTheFiveBiggestCities() {
        String namesList = Cities.stream()
                .sorted(Comparator.comparing(City::getPopulation).reversed())
                .limit(5)
                .map(City::getName)
                .collect(Collectors.joining(", "));
        assertThat(namesList, is(equalTo("Yokohama, Osaka, Nagoya, Sapporo, Kobe")));
    }

    @Test
    public void shouldGetTheTotalJapanesePopulation() {
        int population = Cities.stream()
                .collect(Collectors.summingInt(City::getPopulation));
        assertThat(population, is(equalTo(114_681_154)));
    }

    @Test
    public void shouldGetAverageDensityForPrefecture() {
        Map<String, Double> avg = Cities.stream()
                .collect(Collectors.groupingBy(
                        City::getPrefecture,
                        Collectors.averagingDouble(City::getDensity)
                ));

        assertThat(avg, hasEntry("Nagano", 307.12631578947367));
        assertThat(avg, hasEntry("Yamanashi", 391.3230769230769));
    }

    @Test
    public void shouldGetTheMostPopulatedCityByPrefecture() {
        Comparator<City> populationComp =
                Comparator.comparing(City::getPopulation);

        Map<String, Optional<City>> m = Cities.stream()
                .collect(Collectors.groupingBy(
                        City::getPrefecture,
                        Collectors.maxBy(populationComp)));

        City found = m.get("Tokushima").orElse(null);
        assertThat(m.size(), is(equalTo(47)));
        assertThat(found, is(equalTo(Tokushima())));
    }

    private static City Tokushima() {
        return new City(
                "Tokushima",
                "Tokushima",
                266370,
                191.39,
                1392.0,
                LocalDate.of(1889, 10, 1)
        );
    }
}
