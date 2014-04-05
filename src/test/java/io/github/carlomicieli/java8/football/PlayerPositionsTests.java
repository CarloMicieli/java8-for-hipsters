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

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PlayerPositionsTests {

    @Test
    public void shouldCheckWhetherPlayerIsASpecialist() {
        Player longSnapper = createLongSnapper();
        Player kicker = createPlayerWithPosition("K");
        Player punter = createPlayerWithPosition("P");
        Player safety = createSafety();

        assertThatPlayer_is_aSpecialist(longSnapper);
        assertThatPlayer_is_aSpecialist(kicker);
        assertThatPlayer_is_aSpecialist(punter);
        assertThatPlayer_isNot_aSpecialist(safety);
    }

    @Test
    public void shouldCheckWhetherPlayerIsDefensiveBack() {
        Player longSnapper = createLongSnapper();
        Player freeSafety = createFreeSafety();
        Player strongSafety = createStrongSafety();
        Player cornerBack = createCornerBack();
        Player safety = createSafety();

        assertThatPlayer_isNot_aDefensiveBack(longSnapper);
        assertThatPlayer_is_aDefensiveBack(freeSafety);
        assertThatPlayer_is_aDefensiveBack(strongSafety);
        assertThatPlayer_is_aDefensiveBack(cornerBack);
        assertThatPlayer_is_aDefensiveBack(safety);
    }

    private void assertThatPlayer_is_aDefensiveBack(Player p) {
        assertThat(PlayerPositions.isDefensiveBack(p), is(equalTo(true)));
    }

    private void assertThatPlayer_isNot_aDefensiveBack(Player p) {
        assertThat(PlayerPositions.isDefensiveBack(p), is(equalTo(false)));
    }

    private void assertThatPlayer_isNot_aSpecialist(Player p) {
        assertThat(PlayerPositions.isSpecialist(p), is(equalTo(false)));
    }

    private void assertThatPlayer_is_aSpecialist(Player p) {
        assertThat(PlayerPositions.isSpecialist(p), is(equalTo(true)));
    }

    private Player createPlayerWithPosition(String pos) {
        return new Player("John",
                "Doe",
                pos,
                "Hogwarts",
                "00-0000000",
                "SF",
                "http://www.nfl.com/player/johndoe/profile",
                LocalDate.of(1981, 9, 9),
                73,
                240,
                0,
                19);
    }

    private Player createLongSnapper() {
        return createPlayerWithPosition("LS");
    }

    private Player createSafety() {
        return createPlayerWithPosition("S");
    }

    private Player createCornerBack() {
        return createPlayerWithPosition("CB");
    }

    private Player createStrongSafety() {
        return createPlayerWithPosition("SS");
    }

    private Player createFreeSafety() {
        return createPlayerWithPosition("FS");
    }
}
