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
package io.github.carlomicieli.java8.states;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

/**
 * @author Carlo Micieli
 */
public class State {
    private final String name;
    private final String abbreviation;
    private final LocalDate statehood;
    private final long population;
    private final String capital;
    private final int area;

    public State(String name,
                 String abbreviation,
                 LocalDate statehood,
                 long population,
                 String capital,
                 int area) {

        this.name = name;
        this.abbreviation = abbreviation;
        this.statehood = statehood;
        this.population = population;
        this.capital = capital;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public LocalDate getStatehood() {
        return statehood;
    }

    public long getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public int getArea() {
        return area;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(37, 7)
                .append(this.abbreviation)
                .append(this.name)
                .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof State)) return false;

        State that = (State) obj;

        return new EqualsBuilder()
                .append(this.abbreviation, that.abbreviation)
                .append(this.name, that.name)
                .isEquals();
    }

    @Override
    public String toString() {
        return name + ", " + abbreviation + ", " + statehood + ", " + population + ", " + capital + ", " + area;
    }
}