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

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class InstantTests {

    private static final Instant NOW = Instant.ofEpochMilli(1397909716000L);
    public static final ZoneId AMERICA_CARACAS = ZoneId.of("America/Caracas");

    @Test
    public void shouldParseStringAsInstant() {
        Instant i = Instant.parse("2014-04-19T12:15:16.000Z");
        assertThat(i, is(equalTo(NOW)));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowDateTimeParseExceptionParsingWrongValueForInstant() {
        Instant.parse("NOT VALID");
    }

    @Test
    public void shouldSubtractOneHourFromInstant() {
        Instant i = NOW.minus(Duration.ofHours(1))
                .minus(5, ChronoUnit.MINUTES);
        assertThat(i, is(equalTo(instant(11, 10, 16))));
    }

    @Test
    public void shouldAddTwoHoursToInstant() {
        Instant res = NOW.plus(Duration.ofHours(2))
                .plusSeconds(15);
        assertThat(res, is(equalTo(instant(14, 15, 31))));
    }

    @Test
    public void shouldReturnTheEpochSecondsForInstant() {
        long epochSeconds = NOW.getEpochSecond();
        assertThat(epochSeconds, is(equalTo(1_397_909_716L)));
    }

    @Test
    public void shouldFindWhichInstantIsBeforeTheOther() {
        Instant i1 = instant(12, 10, 45);
        Instant i2 = instant(12, 11, 23);
        assertThat(i1.isBefore(i2), is(true));
        assertThat(i1.isAfter(i2), is(false));
    }

    @Test
    public void shouldTruncateInstantValuesAtHours() {
        Instant i = instant(12, 10, 45)
                .truncatedTo(ChronoUnit.HOURS);
        assertThat(i, is(equalTo(instant(12, 0, 0))));
    }

    @Test
    public void shouldAttachZoneInformationToInstants() {
        Instant i = instant(12, 10, 45);
        ZonedDateTime dt = i.atZone(AMERICA_CARACAS);
        assertThat(dt, is(equalTo(ZonedDateTime.of(2014, 4, 19, 7, 40, 45, 0, AMERICA_CARACAS))));
    }

    private static Instant instant(int hour, int minute, int second) {
        LocalDateTime ldt = LocalDateTime.of(2014, 4, 19, hour, minute, second);
        return instant(ldt);
    }

    private static Instant instant(LocalDateTime ldt) {
        return ldt.toInstant(ZoneOffset.UTC);
    }
}
