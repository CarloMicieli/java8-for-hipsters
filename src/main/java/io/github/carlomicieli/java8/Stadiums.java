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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Carlo Micieli
 */
public final class Stadiums {

    public static List<Stadium> initStadiums() {
        return Collections.unmodifiableList(Arrays.asList(
                Stadium.getBuilder()
                        .name("Levi's Stadium")
                        .capacity(68_000)
                        .location("Santa Clara")
                        .state("CA")
                        .openedYear(2014)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.SF)
                        .build(),
                Stadium.getBuilder()
                        .name("FedEx Field")
                        .capacity(85_000)
                        .location("Landover")
                        .state("MD")
                        .openedYear(1997)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.REDSKINS)
                        .build(),
                Stadium.getBuilder()
                        .name("MetLife Stadium")
                        .capacity(82_566)
                        .location("East Rutherford")
                        .state("NJ")
                        .openedYear(2010)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.SYNTHETIC_TURF)
                        .team(Teams.NYG)
                        .team(Teams.JETS)
                        .build(),
                Stadium.getBuilder()
                        .name("Lambeau Field")
                        .capacity(80_750)
                        .location("Green Bay")
                        .state("WI")
                        .openedYear(1957)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.GB)
                        .build(),
                Stadium.getBuilder()
                        .name("AT&T Stadium")
                        .capacity(105_121)
                        .location("Arlington")
                        .state("TX")
                        .openedYear(2009)
                        .roofType(RoofType.RETRACTABLE)
                        .surface(PlayingSurface.SYNTHETIC_TURF)
                        .team(Teams.DAL)
                        .build(),
                Stadium.getBuilder()
                        .name("Arrowhead Stadium")
                        .capacity(76_416)
                        .location("Kansas City")
                        .state("MO")
                        .openedYear(1972)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.KC)
                        .build(),
                Stadium.getBuilder()
                        .name("Sports Authority Field at Mile High")
                        .capacity(77_160)
                        .location("Denver")
                        .state("CO")
                        .openedYear(2001)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.DEN)
                        .build(),
                Stadium.getBuilder()
                        .name("Sun Life Stadium")
                        .capacity(80_120)
                        .location("Miami Gardens")
                        .state("FL")
                        .openedYear(1987)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.MIA)
                        .build(),
                Stadium.getBuilder()
                        .name("Bank of America Stadium")
                        .capacity(74_113)
                        .location("Charlotte")
                        .state("NC")
                        .openedYear(1996)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.CAR)
                        .build(),
                Stadium.getBuilder()
                        .name("Mercedes-Benz Superdome")
                        .capacity(76_468)
                        .location("New Orleans")
                        .state("LA")
                        .openedYear(1975)
                        .roofType(RoofType.DOMED)
                        .surface(PlayingSurface.SYNTHETIC_TURF)
                        .team(Teams.NO)
                        .build(),
                Stadium.getBuilder()
                        .name("FirstEnergy Stadium")
                        .capacity(73_200)
                        .location("Cleveland")
                        .state("OH")
                        .openedYear(1999)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.CLE)
                        .build(),
                Stadium.getBuilder()
                        .name("Ralph Wilson Stadium")
                        .capacity(73_079)
                        .location("Orchard Park")
                        .state("NY")
                        .openedYear(1973)
                        .roofType(RoofType.OPEN)
                        .surface(PlayingSurface.GRASS)
                        .team(Teams.BUF)
                        .build()

        ));
    }
}
