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

import java.util.Set;
import java.util.TreeSet;

/**
 * It represents a football stadium.
 *
 * @author Carlo Micieli
 */
public final class Stadium implements Comparable<Stadium> {
    private final String name;
    private final int capacity;
    private final String location;
    private final String state;
    private final PlayingSurface surface;
    private final RoofType roofType;
    private final Set<String> teamNames;
    private final int openedYear;

    private Stadium(Builder b) {
        this.name = b.name;
        this.capacity = b.capacity;
        this.location = b.location;
        this.state = b.state;
        this.surface = b.surface;
        this.roofType = b.roofType;
        this.teamNames = b.teamNames;
        this.openedYear = b.openedYear;
    }

    public static Builder newStadium(String name) {
        return new Builder(name);
    }

    public static class Builder {
        private final String name;
        private int capacity;
        private String location;
        private String state;
        private PlayingSurface surface;
        private RoofType roofType;
        private Set<String> teamNames = new TreeSet<>();
        private int openedYear;

        public Builder(String name) {
            this.name = name;
        }

        public Builder with() {
            return this;
        }

        public Builder and() {
            return this;
        }

        public Builder capacity(int c) {
            capacity = c;
            return this;
        }

        public Builder location(String l) {
            location = l;
            return this;
        }

        public Builder state(String s) {
            state = s;
            return this;
        }

        public Builder homeTeam(String t) {
            teamNames.add(t);
            return this;
        }

        public Builder openedIn(int y) {
            openedYear = y;
            return this;
        }

        public Builder grassSurface() {
            return surface(PlayingSurface.GRASS);
        }

        public Builder bermudaGrassSurface() {
            return surface(PlayingSurface.BERMUDA_GRASS);
        }

        public Builder UBUIntensitySeriesSurface() {
            return surface(PlayingSurface.UBU_INTENSITY_SERIES);
        }

        public Builder dessoGrassMasterSurface() {
            return surface(PlayingSurface.DESSO_GRASSMASTER);
        }

        public Builder astroTurfGameDayGrassSurface() {
            return surface(PlayingSurface.ASTROTURF_GAMEDAY_GRASS);
        }

        public Builder kentuckyBluegrassSurface() {
            return surface(PlayingSurface.KENTUCKY_BLUEGRASS);
        }

        public Builder matrixRealGrassArtificialTurfSurface() {
            return surface(PlayingSurface.MATRIX_REALGRASS_TURF);
        }

        public Builder aTurfTitanSurface() {
            return surface(PlayingSurface.A_TURF_TITAN);
        }

        public Builder fieldTurfSurface() {
            return surface(PlayingSurface.FIELD_TURF);
        }

        public Builder sportexeMomentumTurfSurface() {
            return surface(PlayingSurface.SPORTEXE_MOMENTUM_TURF);
        }

        public Builder domedRoof() {
            return roofType(RoofType.DOMED);
        }

        public Builder retractableRoof() {
            return roofType(RoofType.RETRACTABLE);
        }

        public Builder openRoof() {
            return roofType(RoofType.OPEN);
        }

        private Builder surface(PlayingSurface s) {
            surface = s;
            return this;
        }

        private Builder roofType(RoofType r) {
            roofType = r;
            return this;
        }

        public Stadium build() {
            return new Stadium(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public String getState() {
        return state;
    }

    public PlayingSurface getSurface() {
        return surface;
    }

    public RoofType getRoofType() {
        return roofType;
    }

    public Set<String> getTeamNames() {
        return teamNames;
    }

    public int getOpenedYear() {
        return openedYear;
    }

    @Override
    public int compareTo(Stadium that) {
        return this.getName().compareTo(that.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
