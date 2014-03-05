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

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

/**
 * @author Carlo Micieli
 */
public final class Stadiums {
    public static Set<Stadium> initStadiums() {
        return StadiumsList.newList()
            .add(newStadium().withName("FedEx Field")
                .withCapacity(85_000)
                .location("Landover").state("Maryland")
                .withBermudaGrass().withOpenRoof()
                .team("Washington Redskins")
                .openedIn(1997))

            .add(newStadium().withName("MetLife Stadium")
                .withCapacity(82_566)
                .location("East Rutherford").state("New Jersey")
                .withUBUIntensitySeries().withOpenRoof()
                .team("New York Giants")
                .team("New York Jets")
                .openedIn(2010))

            .add(newStadium().withName("Lambeau Field")
                .withCapacity(80_750)
                .location("Green Bay").state("Wisconsin")
                .withDessoGrassMaster().withOpenRoof()
                .team("Green Bay Packers")
                .openedIn(1957))

            .add(newStadium().withName("AT&T Stadium")
                .withCapacity(105_121)
                .location("Arlington").state("Texas")
                .withMatrixRealGrassArtificialTurf().withRetractableRoof()
                .team("Dallas Cowboys")
                .openedIn(2009))

            .add(newStadium().withName("Arrowhead Stadium")
                .withCapacity(76_416)
                .location("Kansas City").state("Missouri")
                .withGrass().withOpenRoof()
                .team("Kansas City Chiefs")
                .openedIn(1972))

            .add(newStadium().withName("Sports Authority Field at Mile High")
                .withCapacity(77_160)
                .location("Denver").state("Colorado")
                .withDessoGrassMaster().withOpenRoof()
                .team("Denver Broncos")
                .openedIn(2001))

            .add(newStadium().withName("Sun Life Stadium")
                .withCapacity(80_120)
                .location("Miami Gardens").state("Florida")
                .withGrass().withOpenRoof()
                .team("Miami Dolphins")
                .openedIn(1987))

            .add(newStadium().withName("Bank of America Stadium")
                .withCapacity(74_113)
                .location("Charlotte").state("North Carolina")
                .withGrass().withOpenRoof()
                .team("Carolina Panthers")
                .openedIn(1996))

            .add(newStadium().withName("Mercedes-Benz Superdome")
                .withCapacity(76_468)
                .location("New Orleans").state("Louisiana")
                .withUBUIntensitySeries().withDomedRoof()
                .team("New Orleans Saints")
                .openedIn(1975))

            .add(newStadium().withName("FirstEnergy Stadium")
                .withCapacity(73_200)
                .location("Cleveland").state("Ohio")
                .withKentuckyBluegrass().withOpenRoof()
                .team("Cleveland Browns")
                .openedIn(1999))

            .add(newStadium().withName("Ralph Wilson Stadium")
                .withCapacity(73_079)
                .location("Orchard Park").state("New York")
                .withATurfTitan().withOpenRoof()
                .team("Buffalo Bills")
                .openedIn(1973))

            .add(newStadium().withName("Georgia Dome")
                .withCapacity(75_624)
                .location("Atlanta").state("Georgia")
                .withFieldTurf().withDomedRoof()
                .team("Atlanta Falcons")
                .openedIn(1992))

            .add(newStadium().withName("Reliant Stadium")
                .withCapacity(71_738)
                .location("Houston").state("Texas")
                .withBermudaGrass().withRetractableRoof()
                .team("Houston Texans")
                .openedIn(2002))

            .add(newStadium().withName("M&T Bank Stadium")
                .withCapacity(74_320)
                .location("Baltimore").state("Maryland")
                .withSportexeMomentumTurf().withOpenRoof()
                .team("Baltimore Ravens")
                .openedIn(1998))

            .add(newStadium().withName("Qualcomm Stadium")
                .withCapacity(70_561)
                .location("San Diego").state("California")
                .withGrass().withOpenRoof()
                .team("San Diego Chargers")
                .openedIn(1967))

            .add(newStadium().withName("LP Field")
                .withCapacity(69_143)
                .location("Nashville").state("Tennessee")
                .withBermudaGrass().withOpenRoof()
                .team("Tennessee Titans")
                .openedIn(1999))

            .add(newStadium().withName("Gillette Stadium")
                .withCapacity(68_756)
                .location("Foxborough").state("Massachusetts")
                .withFieldTurf().withOpenRoof()
                .team("New England Patriots")
                .openedIn(2002))

            .add(newStadium().withName("Lincoln Financial Field")
                .withCapacity(69_144)
                .location("Philadelphia").state(" Pennsylvania")
                .withDessoGrassMaster().withOpenRoof()
                .team("Philadelphia Eagles")
                .openedIn(2003))

            .add(newStadium().withName("Levis Stadium")
                .withCapacity(75_000)
                .location("Santa Clara").state("California")
                .withBermudaGrass().withOpenRoof()
                .team("San Francisco 49ers")
                .openedIn(2014))

            .add(newStadium().withName("EverBank Field")
                .withCapacity(76_867)
                .location("Jacksonville").state("Florida")
                .withBermudaGrass().withOpenRoof()
                .team("Jacksonville Jaguars")
                .openedIn(1995))

            .add(newStadium().withName("CenturyLink Field")
                .withCapacity(72_000)
                .location("Seattle").state("Washington")
                .withFieldTurf().withOpenRoof()
                .team("Seattle Seahawks")
                .openedIn(2002))

            .add(newStadium().withName("Edward Jones Dome")
                .withCapacity(66_000)
                .location("St. Louis").state("Missouri")
                .withAstroTurfGameDayGrass().withDomedRoof()
                .team("St. Louis Rams")
                .openedIn(1995))

            .add(newStadium().withName("Raymond James Stadium")
                .withCapacity(75_000)
                .location("Tampa").state("Florida")
                .withBermudaGrass().withOpenRoof()
                .team("Tampa Bay Buccaneers")
                .openedIn(1998))

            .add(newStadium().withName("Paul Brown Stadium")
                .withCapacity(65_535)
                .location("Cincinnati").state("Ohio")
                .withUBUIntensitySeries().withOpenRoof()
                .team("Cincinnati Bengals")
                .openedIn(2000))

            .add(newStadium().withName("Heinz Field")
                .withCapacity(66_662)
                .location("Pittsburgh").state("Pennsylvania")
                .withGrass().withOpenRoof()
                .team("Pittsburgh Steelers")
                .openedIn(2001))

            .add(newStadium().withName("Ford Field")
                .withCapacity(70_000)
                .location("Detroit").state("Michigan")
                .withFieldTurf().withDomedRoof()
                .team("Detroit Lions")
                .openedIn(2002))

            .add(newStadium().withName("University of Phoenix Stadium")
                .withCapacity(78_600)
                .location("Glendale").state("Arizona")
                .withBermudaGrass().withRetractableRoof()
                .team("Arizona Cardinals")
                .openedIn(2006))

            .add(newStadium().withName("Lucas Oil Stadium")
                .withCapacity(70_000)
                .location("Indianapolis").state("Indiana")
                .withFieldTurf().withRetractableRoof()
                .team("Indianapolis Colts")
                .openedIn(2008))

            .add(newStadium().withName("Soldier Field")
                .withCapacity(62_871)
                .location("Chicago").state("Illinois")
                .withGrass().withOpenRoof()
                .team("Chicago Bears")
                .openedIn(1924))

            .add(newStadium().withName("O.co Coliseum")
                .withCapacity(64_200)
                .location("Oakland").state("California")
                .withGrass().withOpenRoof()
                .team("Oakland Raiders")
                .openedIn(1966))

            .add(newStadium().withName("TCF Bank Stadium")
                .withCapacity(52_000)
                .location("Minneapolis").state("Minnesota")
                .withFieldTurf().withOpenRoof()
                .team("Minnesota Vikings")
                .openedIn(2009))
            .toSet();
    }

    private static Stadium.Builder newStadium() {
        return new Stadium.Builder();
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
