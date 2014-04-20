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

import static io.github.carlomicieli.java8.time.DateTimeHelpers.instantNow;
import static io.github.carlomicieli.java8.time.DateTimeHelpers.laterInstant;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class InstantTests {

    public static final ZoneId AMERICA_CARACAS = ZoneId.of("America/Caracas");
    public static final ZoneId EUROPE_ROME = ZoneId.of("Europe/Rome");

    @Test
    public void shouldParseStringAsInstant() {
        Instant i = Instant.parse("2014-04-19T12:15:16.000Z");
        assertThat(i.getEpochSecond(), is(equalTo(1397909716L)));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowDateTimeParseExceptionParsingWrongValueForInstant() {
        Instant.parse("NOT VALID");
    }

    @Test
    public void shouldSubtractOneHourFromInstant() {
        Instant i = instantNow()
                .minus(Duration.ofHours(1))
                .minus(5, ChronoUnit.MINUTES);
        assertThat(i.toString(), is(equalTo("2014-04-19T11:10:16Z")));
    }

    @Test
    public void shouldAddTwoHoursToInstant() {
        Instant ist = instantNow()
                .plus(Duration.ofHours(2))
                .plusSeconds(15);
        assertThat(ist.toString(), is(equalTo("2014-04-19T14:15:31Z")));
    }

    @Test
    public void shouldFindWhichInstantIsBeforeTheOther() {
        Instant i1 = instantNow();
        Instant i2 = laterInstant();
        assertThat(i1.isBefore(i2), is(true));
        assertThat(i1.isAfter(i2), is(false));
    }

    @Test
    public void shouldTruncateInstantValueAtHoursLevel() {
        Instant i = instantNow()
                .truncatedTo(ChronoUnit.HOURS);
        assertThat(i.toString(), is(equalTo("2014-04-19T12:00:00Z")));
    }

    @Test
    public void shouldAttachTimeZoneInformationToInstants() {
        Instant i = instantNow();
        ZonedDateTime dt = i.atZone(AMERICA_CARACAS);
        assertThat(dt.toString(), is(equalTo("2014-04-19T07:45:16-04:30[America/Caracas]")));
    }

    @Test
    public void shouldCalculatesTheAmountOfTimeUntilAnotherInstant() {
        long diff = instantNow()
                .until(laterInstant(), ChronoUnit.MINUTES);
        assertThat(diff, is(equalTo(4L)));
    }

    @Test
    public void shouldCreateNewInstantsWithFixedClock() {
        Clock fixed = Clock.fixed(Instant.now(), EUROPE_ROME);
        Instant i1 = Instant.now(fixed);
        Instant i2 = Instant.now(fixed);
        assertThat(i1, is(equalTo(i2)));
    }
}
