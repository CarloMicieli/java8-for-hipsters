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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class CityTests {

    public static final LocalDate FOUNDED_AT = LocalDate.of(1889, 10, 1);

    @Test
    public void shouldCreateNewCities() {
        City c = Tokushima();
        assertThat(c.getName(), is(equalTo("Tokushima")));
        assertThat(c.getPrefecture(), is(equalTo("Tokushima")));
        assertThat(c.getPopulation(), is(equalTo(266370)));
        assertThat(c.getArea(), is(equalTo(191.39)));
        assertThat(c.getDensity(), is(equalTo(1392.0)));
        assertThat(c.foundedAt(), is(equalTo(FOUNDED_AT)));
    }

    private static City Tokushima() {
        return new City(
                "Tokushima",
                "Tokushima",
                266370,
                191.39,
                1392.0,
                FOUNDED_AT
        );
    }
}
