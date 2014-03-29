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
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
    public void shouldFindAStadium() {
        Optional<Stadium> sf = Stadiums.findByName("FedEx Field");
        assertThat(sf.isPresent(), is(true));
        assertThat(sf.get().getName(), is(equalTo("FedEx Field")));
    }

}
