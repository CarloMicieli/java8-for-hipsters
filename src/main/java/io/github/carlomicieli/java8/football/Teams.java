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

/**
 * @author Carlo Micieli
 */
public class Teams {
    public static Set<Team> initTeams() {
        final Set<Team> teams = new HashSet<>();
        teams.add(Team.getBuilder()
                .name("Washington Redskins").shortName("WAS")
                .foundedAt(1900)
                .conference("NFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("New York Giants").shortName("NYG")
                .foundedAt(1900)
                .conference("NFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("New York Jets").shortName("NYJ")
                .foundedAt(1900)
                .conference("AFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("Green Bay Packers").shortName("GB")
                .foundedAt(1900)
                .conference("NFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("Dallas Cowboys").shortName("DAL")
                .foundedAt(1900)
                .conference("NFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("Kansas City Chiefs").shortName("KC")
                .foundedAt(1900)
                .conference("AFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("Denver Broncos").shortName("DEN")
                .foundedAt(1900)
                .conference("AFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("Miami Dolphins").shortName("MIA")
                .foundedAt(1900)
                .conference("AFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("Carolina Panthers").shortName("CAR")
                .foundedAt(1900)
                .conference("NFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("New Orleans Saints").shortName("NO")
                .foundedAt(1900)
                .conference("NFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("Cleveland Browns").shortName("CLE")
                .foundedAt(1900)
                .conference("AFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("Buffalo Bills").shortName("BUF")
                .foundedAt(1900)
                .conference("AFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("Atlanta Falcons").shortName("ATL")
                .foundedAt(1900)
                .conference("NFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("Houston Texans").shortName("HOU")
                .foundedAt(1900)
                .conference("AFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("Baltimore Ravens").shortName("BAL")
                .foundedAt(1900)
                .conference("AFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("San Diego Chargers").shortName("SD")
                .foundedAt(1900)
                .conference("AFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("Tennessee Titans").shortName("TEN")
                .foundedAt(1900)
                .conference("AFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("New England Patriots").shortName("PAT")
                .foundedAt(1900)
                .conference("AFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("Philadelphia Eagles").shortName("PHI")
                .foundedAt(1900)
                .conference("NFC").division("East")
                .build());
        teams.add(Team.getBuilder()
                .name("San Francisco 49ers").shortName("SF")
                .foundedAt(1900)
                .conference("NFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("Jacksonville Jaguars").shortName("JAG")
                .foundedAt(1900)
                .conference("AFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("Seattle Seahawks").shortName("SEA")
                .foundedAt(1900)
                .conference("NFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("St. Louis Rams").shortName("STL")
                .foundedAt(1900)
                .conference("NFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("Tampa Bay Buccaneers").shortName("TB")
                .foundedAt(1900)
                .conference("NFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("Cincinnati Bengals").shortName("CIN")
                .foundedAt(1900)
                .conference("AFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("Pittsburgh Steelers").shortName("PIT")
                .foundedAt(1900)
                .conference("AFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("Detroit Lions").shortName("DET")
                .foundedAt(1900)
                .conference("NFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("Arizona Cardinals").shortName("ARI")
                .foundedAt(1900)
                .conference("NFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("Indianapolis Colts").shortName("IND")
                .foundedAt(1900)
                .conference("AFC").division("South")
                .build());
        teams.add(Team.getBuilder()
                .name("Chicago Bears").shortName("CHI")
                .foundedAt(1900)
                .conference("NFC").division("North")
                .build());
        teams.add(Team.getBuilder()
                .name("Oakland Raiders").shortName("OAK")
                .foundedAt(1900)
                .conference("AFC").division("West")
                .build());
        teams.add(Team.getBuilder()
                .name("Minnesota Vikings").shortName("MIN")
                .foundedAt(1900)
                .conference("NFC").division("North")
                .build());
        return Collections.unmodifiableSet(teams);
    }
}

