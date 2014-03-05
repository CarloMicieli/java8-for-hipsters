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

/**
 * @author Carlo Micieli
 */
public final class Team implements Comparable<Team> {

    private final String name;
    private final String shortName;
    private final String conference;
    private final String division;
    private final int foundedAt;

    private Team(Builder b) {
        this.name = b.name;
        this.shortName = b.shortName;
        this.conference = b.conference;
        this.division = b.division;
        this.foundedAt = b.foundedAt;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public int foundedAt() {
        return foundedAt;
    }

    public String getConference() {
        return conference;
    }

    public String getDivision() {
        return division;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String shortName;
        private String conference;
        private String division;
        private int foundedAt;

        public Builder name(String n) {
            this.name = n;
            return this;
        }

        public Builder shortName(String sn) {
            this.shortName = sn;
            return this;
        }

        public Builder conference(String c) {
            this.conference = c;
            return this;
        }

        public Builder division(String c) {
            this.division = c;
            return this;
        }

        public Builder foundedAt(int year) {
            this.foundedAt = year;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }

    @Override
    public int compareTo(Team that) {
        return this.getName().compareTo(that.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
