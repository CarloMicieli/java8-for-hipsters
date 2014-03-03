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

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Carlo Micieli
 */
public final class Stadium {
    private final String name;
    private final int capacity;
    private final String location;
    private final String state;
    private final PlayingSurface surface;
    private final RoofType roofType;
    private final Set<Team> teams;
    private final int openedYear;

    private Stadium(Builder b) {
        this.name = b.name;
        this.capacity = b.capacity;
        this.location = b.location;
        this.state = b.state;
        this.surface = b.surface;
        this.roofType = b.roofType;
        this.teams = b.teams;
        this.openedYear = b.openedYear;
    }

    protected static Builder getBuilder() {
        return new Builder();
    }

    protected static class Builder {
        private String name;
        private int capacity;
        private String location;
        private String state;
        private PlayingSurface surface;
        private RoofType roofType;
        private Set<Team> teams = new TreeSet<>();
        private int openedYear;

        public Builder name(String n) {
            name = n;
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

        public Builder surface(PlayingSurface s) {
            surface = s;
            return this;
        }

        public Builder roofType(RoofType r) {
            roofType = r;
            return this;
        }

        public Builder team(Team t) {
            teams.add(t);
            return this;
        }

        public Builder openedYear(int r) {
            openedYear = r;
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

    public Set<Team> getTeams() {
        return teams;
    }

    public int getOpenedYear() {
        return openedYear;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Stadium> getStadiums() {
        return Stadiums.initStadiums();
    }
}
