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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Carlo Micieli
 */
public final class Player {
    public static final double KILOGRAMS_PER_POUND = 0.45359237;
    public static final double CENTIMETERS_PER_INCH = 2.54;

    private final String firstName;
    private final String lastName;
    private final String position;
    private final String college;
    private final String playerId;
    private final String team;
    private final String profileUrl;
    private final LocalDate birthdate;
    private final int height;
    private final int weight;
    private final Integer number;
    private final Integer yearsPro;

    public Player(String firstName, String lastName, String position, String college,
                  String playerId, String team, String profileUrl, LocalDate birthdate,
                  int height, int weight, Integer number, Integer yearsPro) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.college = college;
        this.playerId = playerId;
        this.team = team;
        this.profileUrl = profileUrl;
        this.birthdate = birthdate;
        this.height = height;
        this.weight = weight;
        this.number = number;
        this.yearsPro = yearsPro;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getCollege() {
        return college;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Optional<String> getTeam() {
        return Optional.ofNullable(team);
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public Optional<Integer> getNumber() {
        return Optional.ofNullable(number);
    }

    public Optional<Integer> getYearsPro() {
        return Optional.ofNullable(yearsPro);
    }

    public int heightInCentimeters() {
        return (int)Math.round(CENTIMETERS_PER_INCH * height);
    }

    public int weightInKg() {
        return (int)Math.round(KILOGRAMS_PER_POUND * weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;

        Player that = (Player) obj;
        return new EqualsBuilder()
                .append(this.firstName, that.firstName)
                .append(this.lastName, that.lastName)
                .append(this.birthdate, that.birthdate)
                .append(this.playerId, that.playerId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(31, 7)
                .append(this.firstName)
                .append(this.lastName)
                .append(this.birthdate)
                .append(this.playerId)
                .hashCode();
    }
}
