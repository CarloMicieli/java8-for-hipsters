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
                .withName("Levi's Stadium")
                .withCapacity(68_000)
                .location("Santa Clara").state("CA")
                .openedIn(2014)
                .team("SF")
                .build();

        assertThat(s.getName(), is(equalTo("Levi's Stadium")));
        assertThat(s.getCapacity(), is(equalTo(68_000)));
        assertThat(s.getLocation(), is(equalTo("Santa Clara")));
        assertThat(s.getState(), is(equalTo("CA")));
        assertThat(s.getOpenedYear(), is(equalTo(2014)));
        assertThat(s.getTeamNames(), hasItem("SF"));
    }

    @Test
    public void shouldAddMoreHomeTeamsForAStadium() {
        Stadium s = Stadium.getBuilder()
                .team("Team1")
                .team("Team2")
                .team("Team3")
                .build();

        assertThat(s.getTeamNames(), hasItem("Team1"));
        assertThat(s.getTeamNames(), hasItem("Team2"));
        assertThat(s.getTeamNames(), hasItem("Team3"));
    }

    @Test
    public void shouldSet_Open_AsRoofType() {
        Stadium s = Stadium.getBuilder().withOpenRoof().build();
        assertThat(s.getRoofType(), is(equalTo(RoofType.OPEN)));
    }

    @Test
    public void shouldSet_Domed_AsRoofType() {
        Stadium s = Stadium.getBuilder().withDomedRoof().build();
        assertThat(s.getRoofType(), is(equalTo(RoofType.DOMED)));
    }

    @Test
    public void shouldSet_RetractableRoof_AsRoofType() {
        Stadium s = Stadium.getBuilder().withRetractableRoof().build();
        assertThat(s.getRoofType(), is(equalTo(RoofType.RETRACTABLE)));
    }

    @Test
    public void shouldSet_Grass_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withGrass().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.GRASS)));
    }

    @Test
    public void shouldSet_BermudaGrass_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withBermudaGrass().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.BERMUDA_GRASS)));
    }

    @Test
    public void shouldSet_UBUIntensitySeries_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withUBUIntensitySeries().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.UBU_INTENSITY_SERIES)));
    }

    @Test
    public void shouldSet_TurfTitan_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withATurfTitan().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.A_TURF_TITAN)));
    }

    @Test
    public void shouldSet_DessoGrassMaster_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withDessoGrassMaster().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.DESSO_GRASSMASTER)));
    }

    @Test
    public void shouldSet_KentuckyBluegrass_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withKentuckyBluegrass().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.KENTUCKY_BLUEGRASS)));
    }

    @Test
    public void shouldSet_MatrixRealGrassArtificialTurf_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withMatrixRealGrassArtificialTurf().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.MATRIX_REALGRASS_TURF)));
    }

    @Test
    public void shouldSet_SportexeMomentumTurf_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withSportexeMomentumTurf().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.SPORTEXE_MOMENTUM_TURF)));
    }

    @Test
    public void shouldSet_AstroTurfGameDayGrass_AsPlayingSurface() {
        Stadium s = Stadium.getBuilder().withAstroTurfGameDayGrass().build();
        assertThat(s.getSurface(), is(equalTo(PlayingSurface.ASTROTURF_GAMEDAY_GRASS)));
    }

    @Test
    public void shouldProduceStringRepresentationForTeams() {
        Stadium s = Stadium.getBuilder()
                .withName("Levi's Stadium")
                .build();
        assertThat(s.toString(), is(equalTo("Levi's Stadium")));
    }
}
