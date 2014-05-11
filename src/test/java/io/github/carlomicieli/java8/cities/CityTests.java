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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Carlo Micieli
 */
public class CityTests {

    public static final LocalDate FOUNDED_AT = LocalDate.of(1889, 10, 1);

    @Test
    public void shouldCreateNewCities() {
        City c = tokushima();

        assertThat(c.getName()).isEqualTo("Tokushima");
        assertThat(c.getPrefecture()).isEqualTo("Tokushima");
        assertThat(c.getPopulation()).isEqualTo(266370);
        assertThat(c.getArea()).isEqualTo(191.39);
        assertThat(c.getDensity()).isEqualTo(1392.0);
        assertThat(c.foundedAt()).isEqualTo(FOUNDED_AT);
    }

    @Test
    public void shouldCheckWhetherTwoCitiesAreEquals() {
        City a = tokushima();
        City b = tokushima();

        assertThat(a).isEqualTo(b);
    }

    @Test
    public void shouldCheckWhetherTwoCitiesAreDifferent() {
        City a = tokushima();
        City b = nagoya();

        assertThat(a).isNotEqualTo(b);
    }

    @Test
    public void shouldReturnHashCodeForCities() {
        City a = tokushima();
        City b = tokushima();

        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    private static City tokushima() {
        return new City(
                "Tokushima",
                "Tokushima",
                266370,
                191.39,
                1392.0,
                FOUNDED_AT
        );
    }

    private static City nagoya() {
        return new City(
                "Nagoya",
                "Aichi",
                2_239_464,
                326.45,
                6_860.0,
                FOUNDED_AT
        );
    }
}
