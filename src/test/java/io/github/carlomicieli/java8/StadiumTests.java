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
package io.github.carlomicieli.java8;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StadiumTests {

    @Test
    public void shouldCreateNewStadiums() {
        Stadium s = Stadium.getBuilder()
                .name("Levi's Stadium")
                .capacity(68_000)
                .location("Santa Clara")
                .state("CA")
                .openedYear(2014)
                .roofType(RoofType.OPEN)
                .surface(PlayingSurface.GRASS)
                .team(Teams.SF)
                .build();

        assertThat(s.getName(), is(equalTo("Levi's Stadium")));
        assertThat(s.getCapacity(), is(equalTo(68_000)));
        assertThat(s.getLocation(), is(equalTo("Santa Clara")));
        assertThat(s.getState(), is(equalTo("CA")));
        assertThat(s.getOpenedYear(), is(equalTo(2014)));
        assertThat(s.getRoofType(), is(equalTo(RoofType.OPEN)));
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.GRASS)));
        assertThat(s.getTeams(), hasItem(Teams.SF));
    }

    @Test
    public void shouldGetTheStadiumsList() {
        List<Stadium> stadiums = Stadium.getStadiums();
        assertThat(stadiums.size(), is(equalTo(12)));
    }
}
