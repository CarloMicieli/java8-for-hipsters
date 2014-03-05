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
public class TeamTests {

    @Test
    public void shouldCreateNewTeams() {
        Team team = Team.getBuilder()
            .name("Washington Redskins").shortName("WAS")
            .foundedAt(1900)
            .conference("NFC").division("East")
            .build();

        assertThat(team.getName(), is(equalTo("Washington Redskins")));
        assertThat(team.getShortName(), is(equalTo("WAS")));
        assertThat(team.foundedAt(), is(equalTo(1900)));
        assertThat(team.getConference(), is(equalTo("NFC")));
        assertThat(team.getDivision(), is(equalTo("East")));
    }

}
