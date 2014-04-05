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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PlayerTests {

    @Test
    public void shouldCreateNewPlayers() {
        Player patrickWillis = createPlayerForPatrickWillis();
        assertThatPlayer_Is_PatrickWillis(patrickWillis);
    }

    @Test
    public void playersShouldHaveOptionalTeam() {
        Player withTeam = createPlayerWithTeam("SF");
        Player withoutTeam = createPlayerWithoutTeam();

        assertThat(withTeam.getTeam().get(), is(equalTo("SF")));
        assertThat(withoutTeam.getTeam().orElse(null), is(nullValue()));
    }

    @Test
    public void playersShouldHaveOptionalJerseyNumber() {
        Player withNumber = createPlayerWithNumber(99);
        Player withoutNumber = createPlayerWithoutNumber();

        assertThat(withNumber.getNumber().get(), is(equalTo(99)));
        assertThat(withoutNumber.getNumber().orElse(null), is(nullValue()));
    }

    @Test
    public void playersShouldHaveOptionalNumberOfYearsAmongPro() {
        Player withYearsPro = createPlayerWithYearsPro(9);
        Player withoutYearsPro = createPlayerWithoutYearsPro();

        assertThat(withYearsPro.getYearsPro().get(), is(equalTo(9)));
        assertThat(withoutYearsPro.getYearsPro().orElse(null), is(nullValue()));
    }

    @Test
    public void shouldReturnPlayersHeightInCentimeters() {
        Player patrickWillis = createPlayerForPatrickWillis();
        assertThat(patrickWillis.heightInCentimeters(), is(equalTo(185)));
    }

    @Test
    public void shouldReturnPlayersWeightInKilograms() {
        Player patrickWillis = createPlayerForPatrickWillis();
        assertThat(patrickWillis.weightInKg(), is(equalTo(109)));
    }

    @Test
    public void shouldCheckWhetherTwoPlayersAreDifferent() {
        Player x = createPlayerForPatrickWillis();
        Player y = createAnotherPlayer();

        assertThat(x.equals(y), is(equalTo(false)));
    }

    @Test
    public void shouldCheckWhetherTwoPlayersAreEquals() {
        Player x = createPlayerForPatrickWillis();
        Player y = createPlayerForPatrickWillis();

        assertThat(x.equals(x), is(equalTo(true)));
        assertThat(x.equals(y), is(equalTo(true)));
    }

    @Test
    public void shouldCalculateHashCodeForPlayers() {
        Player x = createPlayerForPatrickWillis();
        Player y = createPlayerForPatrickWillis();
        assertThat(x.hashCode(), is(equalTo(y.hashCode())));
    }

    private Player createPlayerWithNumber(int number) {
        return createPlayer(null, number, null);
    }

    private Player createPlayerWithYearsPro(int yearsPro) {
        return createPlayer(null, null, yearsPro);
    }

    private Player createPlayerWithTeam(String team) {
        return createPlayer(team, null, null);
    }

    private Player createPlayerWithoutYearsPro() {
        return createAnotherPlayer();
    }

    private Player createPlayerWithoutTeam() {
        return createAnotherPlayer();
    }

    private Player createPlayerWithoutNumber() {
        return createAnotherPlayer();
    }

    private Player createAnotherPlayer() {
        return createPlayer(null, null, null);
    }

    private Player createPlayer(String team, Integer number, Integer yearsPro) {
        return new Player("John",
                "Doe",
                "FS",
                "Hogwarts",
                "00-0000000",
                team,
                "http://www.nfl.com/player/johndoe/profile",
                LocalDate.of(1981, 9, 9),
                73,
                240,
                number,
                yearsPro);
    }

    private Player createPlayerForPatrickWillis() {
        return new Player("Patrick", "Willis",
                "ILB",
                "Mississippi",
                "00-0025398",
                "SF",
                "http://www.nfl.com/player/patrickwillis/2495781/profile",
                LocalDate.of(1985, 1, 25),
                73,
                240,
                52,
                8);
    }

    private void assertThatPlayer_Is_PatrickWillis(Player p) {
        assertThat(p.getFirstName(), is(equalTo("Patrick")));
        assertThat(p.getLastName(), is(equalTo("Willis")));
        assertThat(p.getCollege(), is(equalTo("Mississippi")));
        assertThat(p.getPlayerId(), is(equalTo("00-0025398")));
        assertThat(p.getTeam().orElse(null), is(equalTo("SF")));
        assertThat(p.getProfileUrl(), is(equalTo("http://www.nfl.com/player/patrickwillis/2495781/profile")));
        assertThat(p.getBirthdate(), is(equalTo(LocalDate.of(1985, 1, 25))));
        assertThat(p.getHeight(), is(equalTo(73)));
        assertThat(p.getWeight(), is(equalTo(240)));
        assertThat(p.getNumber().orElse(null), is(equalTo(52)));
        assertThat(p.getYearsPro().orElse(null), is(equalTo(8)));
    }

}
