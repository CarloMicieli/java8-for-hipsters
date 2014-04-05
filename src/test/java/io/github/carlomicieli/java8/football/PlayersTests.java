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
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PlayersTests {

    @Test
    public void shouldLoadPlayersData() {
        long count = Players.stream().count();
        assertThat(count, is(greaterThan(0L)));
    }

    @Test
    public void shouldLoadPlayersPositions() {
        List<String> positions = Players.stream()
                .map(Player::getPosition)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        assertThat(positions, hasSize(25));
        assertThat(positions, hasItems("C", "CB", "DB", "DE", "DT", "FB", "FS",
                "G", "ILB", "K", "LB", "LS", "MLB", "NT", "OG", "OL",
                "OLB", "OT", "P", "QB", "RB", "SS", "T", "TE", "WR"));
    }


}
