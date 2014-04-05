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

import io.github.carlomicieli.java8.utils.OrdinalNumber;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Carlo Micieli
 */
public final class DraftPick {
    private final int year;
    private final int round;
    private final int pick;
    private final int overall;
    private final String team;
    private final String name;
    private final String position;
    private final String college;
    private final int lastYear;

    public DraftPick(int year, int round, int pick, int overall, String team, String name, String position, String college, int lastYear) {
        this.year = year;
        this.round = round;
        this.pick = pick;
        this.overall = overall;
        this.team = team;
        this.name = name;
        this.position = position;
        this.college = college;
        this.lastYear = lastYear;
    }

    public int getYear() {
        return year;
    }

    public int getRound() {
        return round;
    }

    public int getPick() {
        return pick;
    }

    public int getOverall() {
        return overall;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getCollege() {
        return college;
    }

    public int getLastYear() {
        return lastYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DraftPick)) return false;

        DraftPick that = (DraftPick) obj;
        return new EqualsBuilder()
                .append(this.year, that.year)
                .append(this.round, that.round)
                .append(this.overall, that.overall)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(31, 7)
                .append(this.year)
                .append(this.round)
                .append(this.overall)
                .hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(OrdinalNumber.of(round)).append(" round, ")
                .append(OrdinalNumber.of(pick)).append(" pick (")
                .append(OrdinalNumber.of(overall)).append(" overall) ")
                .append(name)
                .append(" (").append(college).append(")")
                .toString();

    }
}
