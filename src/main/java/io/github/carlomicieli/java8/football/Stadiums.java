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

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import static io.github.carlomicieli.java8.football.Stadium.newStadium;

/**
 * @author Carlo Micieli
 */
public final class Stadiums {
    public static Set<Stadium> initStadiums() {
        return StadiumsList.newList()
                .add(newStadium("FedEx Field")
                        .with().capacity(85_000)
                        .and().location("Landover").state("Maryland")
                        .with().bermudaGrassSurface()
                        .and().openRoof()
                        .with().homeTeam("Washington Redskins")
                        .openedIn(1997))

                .add(newStadium("MetLife Stadium")
                        .with().capacity(82_566)
                        .and().location("East Rutherford").state("New Jersey")
                        .with().UBUIntensitySeriesSurface()
                        .and().openRoof()
                        .with().homeTeam("New York Giants").and().homeTeam("New York Jets")
                        .openedIn(2010))

                .add(newStadium("Lambeau Field")
                        .with().capacity(80_750)
                        .and().location("Green Bay").state("Wisconsin")
                        .with().dessoGrassMasterSurface()
                        .and().openRoof()
                        .with().homeTeam("Green Bay Packers")
                        .openedIn(1957))

                .add(newStadium("AT&T Stadium")
                        .with().capacity(105_121)
                        .and().location("Arlington").state("Texas")
                        .with().matrixRealGrassArtificialTurfSurface()
                        .and().retractableRoof()
                        .with().homeTeam("Dallas Cowboys")
                        .openedIn(2009))

                .add(newStadium("Arrowhead Stadium")
                        .with().capacity(76_416)
                        .and().location("Kansas City").state("Missouri")
                        .with().grassSurface()
                        .and().openRoof()
                        .with().homeTeam("Kansas City Chiefs")
                        .openedIn(1972))

                .add(newStadium("Sports Authority Field at Mile High")
                        .with().capacity(77_160)
                        .and().location("Denver").state("Colorado")
                        .with().dessoGrassMasterSurface()
                        .and().openRoof()
                        .with().homeTeam("Denver Broncos")
                        .openedIn(2001))

                .add(newStadium("Sun Life Stadium")
                        .with().capacity(80_120)
                        .and().location("Miami Gardens").state("Florida")
                        .grassSurface().openRoof()
                        .homeTeam("Miami Dolphins")
                        .openedIn(1987))

                .add(newStadium("Bank of America Stadium")
                        .with().capacity(74_113)
                        .and().location("Charlotte").state("North Carolina")
                        .with().grassSurface()
                        .and().openRoof()
                        .with().homeTeam("Carolina Panthers")
                        .openedIn(1996))

                .add(newStadium("Mercedes-Benz Superdome")
                        .with().capacity(76_468)
                        .and().location("New Orleans").state("Louisiana")
                        .with().UBUIntensitySeriesSurface()
                        .and().domedRoof()
                        .with().homeTeam("New Orleans Saints")
                        .openedIn(1975))

                .add(newStadium("FirstEnergy Stadium")
                        .with().capacity(73_200)
                        .and().location("Cleveland").state("Ohio")
                        .with().kentuckyBluegrassSurface()
                        .and().openRoof()
                        .with().homeTeam("Cleveland Browns")
                        .openedIn(1999))

                .add(newStadium("Ralph Wilson Stadium")
                        .with().capacity(73_079)
                        .and().location("Orchard Park").state("New York")
                        .with().aTurfTitanSurface()
                        .and().openRoof()
                        .with().homeTeam("Buffalo Bills")
                        .openedIn(1973))

                .add(newStadium("Georgia Dome")
                        .with().capacity(75_624)
                        .and().location("Atlanta").state("Georgia")
                        .with().fieldTurfSurface()
                        .and().domedRoof()
                        .with().homeTeam("Atlanta Falcons")
                        .openedIn(1992))

                .add(newStadium("Reliant Stadium")
                        .with().capacity(71_738)
                        .and().location("Houston").state("Texas")
                        .with().bermudaGrassSurface()
                        .and().retractableRoof()
                        .with().homeTeam("Houston Texans")
                        .openedIn(2002))

                .add(newStadium("M&T Bank Stadium")
                        .with().capacity(74_320)
                        .and().location("Baltimore").state("Maryland")
                        .with().sportexeMomentumTurfSurface()
                        .and().openRoof()
                        .with().homeTeam("Baltimore Ravens")
                        .openedIn(1998))

                .add(newStadium("Qualcomm Stadium")
                        .with().capacity(70_561)
                        .and().location("San Diego").state("California")
                        .with().grassSurface()
                        .and().openRoof()
                        .with().homeTeam("San Diego Chargers")
                        .openedIn(1967))

                .add(newStadium("LP Field")
                        .with().capacity(69_143)
                        .and().location("Nashville").state("Tennessee")
                        .with().bermudaGrassSurface()
                        .and().openRoof()
                        .with().homeTeam("Tennessee Titans")
                        .openedIn(1999))

                .add(newStadium("Gillette Stadium")
                        .with().capacity(68_756)
                        .and().location("Foxborough").state("Massachusetts")
                        .with().fieldTurfSurface()
                        .and().openRoof()
                        .with().homeTeam("New England Patriots")
                        .openedIn(2002))

                .add(newStadium("Lincoln Financial Field")
                        .with().capacity(69_144)
                        .and().location("Philadelphia").state(" Pennsylvania")
                        .with().dessoGrassMasterSurface()
                        .and().openRoof()
                        .with().homeTeam("Philadelphia Eagles")
                        .openedIn(2003))

                .add(newStadium("Levis Stadium")
                        .with().capacity(75_000)
                        .and().location("Santa Clara").state("California")
                        .with().bermudaGrassSurface()
                        .and().openRoof()
                        .with().homeTeam("San Francisco 49ers")
                        .openedIn(2014))

                .add(newStadium("EverBank Field")
                        .with().capacity(76_867)
                        .and().location("Jacksonville").state("Florida")
                        .with().bermudaGrassSurface()
                        .and().openRoof()
                        .with().homeTeam("Jacksonville Jaguars")
                        .openedIn(1995))

                .add(newStadium("CenturyLink Field")
                        .with().capacity(72_000)
                        .and().location("Seattle").state("Washington")
                        .with().fieldTurfSurface()
                        .and().openRoof()
                        .with().homeTeam("Seattle Seahawks")
                        .openedIn(2002))

                .add(newStadium("Edward Jones Dome")
                        .with().capacity(66_000)
                        .and().location("St. Louis").state("Missouri")
                        .with().astroTurfGameDayGrassSurface()
                        .and().domedRoof()
                        .with().homeTeam("St. Louis Rams")
                        .openedIn(1995))

                .add(newStadium("Raymond James Stadium")
                        .with().capacity(75_000)
                        .and().location("Tampa").state("Florida")
                        .with().bermudaGrassSurface()
                        .and().openRoof()
                        .with().homeTeam("Tampa Bay Buccaneers")
                        .openedIn(1998))

                .add(newStadium("Paul Brown Stadium")
                        .with().capacity(65_535)
                        .and().location("Cincinnati").state("Ohio")
                        .with().UBUIntensitySeriesSurface()
                        .and().openRoof()
                        .with().homeTeam("Cincinnati Bengals")
                        .openedIn(2000))

                .add(newStadium("Heinz Field")
                        .with().capacity(66_662)
                        .and().location("Pittsburgh").state("Pennsylvania")
                        .with().grassSurface()
                        .and().openRoof()
                        .with().homeTeam("Pittsburgh Steelers")
                        .openedIn(2001))

                .add(newStadium("Ford Field")
                        .with().capacity(70_000)
                        .and().location("Detroit").state("Michigan")
                        .with().fieldTurfSurface()
                        .and().domedRoof()
                        .with().homeTeam("Detroit Lions")
                        .openedIn(2002))

                .add(newStadium("University of Phoenix Stadium")
                        .with().capacity(78_600)
                        .and().location("Glendale").state("Arizona")
                        .with().bermudaGrassSurface()
                        .and().retractableRoof()
                        .with().homeTeam("Arizona Cardinals")
                        .openedIn(2006))

                .add(newStadium("Lucas Oil Stadium")
                        .with().capacity(70_000)
                        .and().location("Indianapolis").state("Indiana")
                        .with().fieldTurfSurface()
                        .and().retractableRoof()
                        .with().homeTeam("Indianapolis Colts")
                        .openedIn(2008))

                .add(newStadium("Soldier Field")
                        .with().capacity(62_871)
                        .and().location("Chicago").state("Illinois")
                        .with().grassSurface()
                        .and().openRoof()
                        .with().homeTeam("Chicago Bears")
                        .openedIn(1924))

                .add(newStadium("O.co Coliseum")
                        .with().capacity(64_200)
                        .and().location("Oakland").state("California")
                        .with().grassSurface()
                        .and().openRoof()
                        .with().homeTeam("Oakland Raiders")
                        .openedIn(1966))

                .add(newStadium("TCF Bank Stadium")
                        .with().capacity(52_000)
                        .and().location("Minneapolis").state("Minnesota")
                        .with().fieldTurfSurface()
                        .and().openRoof()
                        .with().homeTeam("Minnesota Vikings")
                        .openedIn(2009))
                .toSet();
    }

    private static class StadiumsList {
        private final Set<Stadium> stadiums;

        private StadiumsList add(Stadium.Builder sb) {
            stadiums.add(sb.build());
            return this;
        }

        private static StadiumsList newList() {
            return new StadiumsList();
        }

        private StadiumsList() {
            stadiums = new HashSet<>();
        }

        private Set<Stadium> toSet() {
            return Collections.unmodifiableSet(stadiums);
        }
    }

}
