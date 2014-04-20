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
package io.github.carlomicieli.java8.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author Carlo Micieli
 */
public class DateTimeHelpers {

    public static Instant instantNow() {
        return Instant.now(fixedClock());
    }

    public static Instant laterInstant() {
        return Instant.now(fixedClock()).plus(256, ChronoUnit.SECONDS);
    }

    public static Duration positiveDuration() {
        Instant i1 = instantNow();
        Instant i2 = laterInstant();

        return Duration.between(i1, i2);
    }

    public static Duration zeroDuration() {
        Instant i = Instant.now();
        return Duration.between(i, i);
    }

    public static Duration negativeDuration() {
        return positiveDuration().negated();
    }

    public static LocalDate currentDate() {
        return LocalDate.of(2014, 4, 19);
    }

    public static LocalDate nextProgrammersDay() {
        return LocalDate.ofYearDay(2015, 256);
    }

    private final static ZoneId ZONE = ZoneId.of("Europe/Rome");

    private static Clock fixedClock() {
        return Clock.fixed(Instant.ofEpochMilli(1397909716000L), ZONE);
    }

}
