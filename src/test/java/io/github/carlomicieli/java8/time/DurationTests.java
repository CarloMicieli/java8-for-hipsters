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

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static io.github.carlomicieli.java8.time.DateTimeHelpers.*;
import static java.time.temporal.ChronoUnit.NANOS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Carlo Micieli
 */
public class DurationTests {

    @Test
    public void shouldFindDurationBetweenTwoInstants() {
        Duration dur = positiveDuration();
        assertThat(dur.getSeconds(), is(equalTo(256L)));
        assertThat(dur.toMinutes(), is(equalTo(4L)));
    }

    @Test
    public void shouldCheckWhetherDurationsArePositiveOrNegative() {
        Duration d = positiveDuration();
        Duration dn = d.negated();

        assertThat(d.isNegative(), is(false));
        assertThat(dn.isNegative(), is(true));
    }

    @Test
    public void shouldReturnAbsoluteValueForDurations() {
        Duration d = negativeDuration();

        Duration abs = d.abs();

        assertThat(d.getSeconds(), is(lessThan(0L)));
        assertThat(abs.getSeconds(), is(greaterThan(0L)));
    }

    @Test
    public void shouldCheckWhetherHasZeroDuration() {
        Duration d = zeroDuration();
        assertThat(d.getSeconds(), is(equalTo(0L)));
        assertThat(d.isZero(), is(equalTo(true)));
    }

    @Test
    public void shouldSupportOnlySecondsAndNanosAsTemporalUnits() {
        Duration d = positiveDuration();

        List<TemporalUnit> supported = d.getUnits();

        assertThat(supported, contains(SECONDS, NANOS));
    }

    @Test
    public void shouldModifyDurations() {
        Duration d = positiveDuration();
        Duration res = d.multipliedBy(2)
                .plusSeconds(30)
                .minus(1, ChronoUnit.MINUTES);
        assertThat(res.getSeconds(), is(equalTo(482L)));
    }

    @Test
    public void shouldObtainNewDurationFromGivenValuesForHoursMinutesOrSeconds() {
        Duration d1 = Duration.ofHours(1);
        Duration d2 = Duration.ofMinutes(60);
        Duration d3 = Duration.ofSeconds(3_600);
        assertThat(d1, is(equalTo(d2)));
        assertThat(d1, is(equalTo(d3)));
    }

    @Test
    public void shouldProduceStringRepresentationsForDuration() {
        String s = Duration.ofHours(4)
                .plusMinutes(24)
                .plusSeconds(36).toString();
        assertThat(s, is(equalTo("PT4H24M36S")));
    }

    @Test
    public void shouldParseStringsToDurations() {
        Duration d = Duration.parse("PT4H24M36S");

        assertThat(d.toHours(), is(equalTo(4L)));
        assertThat(d.toMinutes(), is(equalTo(264L)));
        assertThat(d.getSeconds(), is(equalTo(15_876L)));
    }

    @Test
    public void shouldReturnDurationCopyWithTheProvidedSeconds() {
        Duration d = positiveDuration();
        Duration d2 = d.withSeconds(42);

        assertThat(d.getSeconds(), is(equalTo(256L)));
        assertThat(d2.getSeconds(), is(equalTo(42L)));
    }

    @Test
    public void shouldAddsDurationToInstant() {
        Instant now = instantNow();
        Duration oneMinute = Duration.ofMinutes(1);

        Instant later = now.plus(oneMinute);

        assertThat(now.toString(), is(equalTo("2014-04-19T12:15:16Z")));
        assertThat(later.toString(), is(equalTo("2014-04-19T12:16:16Z")));
    }
}
