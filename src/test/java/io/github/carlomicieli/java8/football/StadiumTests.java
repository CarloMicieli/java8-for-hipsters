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

import static io.github.carlomicieli.java8.football.Stadium.newStadium;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * @author Carlo Micieli
 */
public class StadiumTests {

    @Test
    public void shouldCreateNewStadiums() {
        Stadium s = newStadium("Levi's Stadium")
                .id(1)
                .capacity(68_000)
                .location("Santa Clara").state("CA")
                .openedIn(2014)
                .homeTeam("SF")
                .build();

        assertThat(s.getId(), is(equalTo(1)));
        assertThat(s.getName(), is(equalTo("Levi's Stadium")));
        assertThat(s.getCapacity(), is(equalTo(68_000)));
        assertThat(s.getLocation(), is(equalTo("Santa Clara")));
        assertThat(s.getState(), is(equalTo("CA")));
        assertThat(s.getOpenedYear(), is(equalTo(2014)));
        assertThat(s.getTeamNames(), hasItem("SF"));
    }

    @Test
    public void shouldAddMoreHomeTeamsForAStadium() {
        Stadium s = newStadium("Stadium's Name")
                .homeTeam("Team1")
                .homeTeam("Team2")
                .homeTeam("Team3")
                .build();

        assertThat(s.getTeamNames(), hasItem("Team1"));
        assertThat(s.getTeamNames(), hasItem("Team2"));
        assertThat(s.getTeamNames(), hasItem("Team3"));
    }

    @Test
    public void shouldSet_Open_AsRoofType() {
        Stadium s = newStadium("Stadium's Name").openRoof().build();
        assertThat(s.getRoofType(), is(equalTo(RoofType.OPEN)));
    }

    @Test
    public void shouldSet_Domed_AsRoofType() {
        Stadium s = newStadium("Stadium's Name").domedRoof().build();
        assertThat(s.getRoofType(), is(equalTo(RoofType.DOMED)));
    }

    @Test
    public void shouldSet_RetractableRoof_AsRoofType() {
        Stadium s = newStadium("Stadium's Name").retractableRoof().build();
        assertThat(s.getRoofType(), is(equalTo(RoofType.RETRACTABLE)));
    }

    @Test
    public void shouldSet_Grass_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").grassSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.GRASS)));
    }

    @Test
    public void shouldSet_BermudaGrass_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").bermudaGrassSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.BERMUDA_GRASS)));
    }

    @Test
    public void shouldSet_UBUIntensitySeries_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").UBUIntensitySeriesSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.UBU_INTENSITY_SERIES)));
    }

    @Test
    public void shouldSet_TurfTitan_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").aTurfTitanSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.A_TURF_TITAN)));
    }

    @Test
    public void shouldSet_DessoGrassMaster_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").dessoGrassMasterSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.DESSO_GRASSMASTER)));
    }

    @Test
    public void shouldSet_FieldTurfSurface_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").fieldTurfSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.FIELD_TURF)));
    }

    @Test
    public void shouldSet_KentuckyBluegrass_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").kentuckyBluegrassSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.KENTUCKY_BLUEGRASS)));
    }

    @Test
    public void shouldSet_MatrixRealGrassArtificialTurf_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").matrixRealGrassArtificialTurfSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.MATRIX_REALGRASS_TURF)));
    }

    @Test
    public void shouldSet_SportexeMomentumTurf_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").sportexeMomentumTurfSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.SPORTEXE_MOMENTUM_TURF)));
    }

    @Test
    public void shouldSet_AstroTurfGameDayGrass_AsPlayingSurface() {
        Stadium s = newStadium("Stadium's Name").astroTurfGameDayGrassSurface().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.ASTROTURF_GAMEDAY_GRASS)));
    }

    @Test
    public void shouldProduceStringRepresentationForTeams() {
        Stadium s = newStadium("Levi's Stadium")
                .build();
        assertThat(s.toString(), is(equalTo("Levi's Stadium")));
    }

    @Test
    public void shouldImplementsComparableForStadiums() {
        Stadium a = newStadium("AAAAAA").build();
        Stadium b = newStadium("ZZZZZZ").build();

        assertThat(a.compareTo(a), is(0));
        assertThat(a.compareTo(b), is(lessThan(0)));
        assertThat(b.compareTo(a), is(greaterThan(0)));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrow_NullPointerException_IfTheSpecifiedStadiumIsNull() {
        Stadium a = newStadium("AAAAAAA").build();
        a.compareTo(null);
    }

    @Test
    public void shouldCompareStadiumsCapacity() {
        Stadium a = newStadium("AAAAAA").capacity(1000).build();
        Stadium b = newStadium("BBBBBB").capacity(500).build();

        assertThat(Stadium.compareByCapacity(a, a), is(equalTo(0)));
        assertThat(Stadium.compareByCapacity(a, b), is(greaterThan(0)));
        assertThat(Stadium.compareByCapacity(b, a), is(lessThan(0)));
    }
}
