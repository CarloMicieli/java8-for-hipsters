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

import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static io.github.carlomicieli.java8.time.DateTimeHelpers.currentDate;
import static io.github.carlomicieli.java8.time.DateTimeHelpers.nextProgrammersDay;
import static java.time.temporal.ChronoUnit.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Carlo Micieli
 */
public class PeriodTests {

    @Test
    public void shouldCreateNewPeriodsBetweenTwoLocalDates() {
        Period p = Period.between(currentDate(), nextProgrammersDay());

        assertThat(p.getYears(), is(equalTo(1)));
        assertThat(p.getMonths(), is(equalTo(4)));
        assertThat(p.getDays(), is(equalTo(25)));
    }

    @Test
    public void shouldSupportOnlyYearsMonthsAndDays() {
        Period p = Period.ofDays(1);
        List<TemporalUnit> supported = p.getUnits();

        assertThat(supported, hasSize(3));
        assertThat(supported, contains(YEARS, MONTHS, DAYS));
    }

    @Test
    public void shouldNormalizeYearAndMonthValuesForPeriods() {
        Period p = Period.of(1, 15, 40);
        Period pn = p.normalized();

        assertThat(p.getYears(), is(equalTo(1)));
        assertThat(p.getMonths(), is(equalTo(15)));
        assertThat(p.getDays(), is(equalTo(40)));

        assertThat(pn.getYears(), is(equalTo(2)));
        assertThat(pn.getMonths(), is(equalTo(3)));
        assertThat(pn.getDays(), is(equalTo(40)));
    }

    @Test
    public void shouldGetTotalMonthsForAPeriod() {
        Period p = Period.of(1, 15, 40);
        assertThat(p.toTotalMonths(), is(equalTo(27L)));
    }

    @Test
    public void shouldProduceStringRepresentationForPeriods() {
        Period p = Period.of(1, 15, 40);
        assertThat(p.toString(), is(equalTo("P1Y15M40D")));
    }

    @Test
    public void shouldParseStringToPeriods() {
        Period p = Period.parse("P1Y15M40D");
        assertThat(p.getYears(), is(equalTo(1)));
        assertThat(p.getMonths(), is(equalTo(15)));
        assertThat(p.getDays(), is(equalTo(40)));
    }

    @Test
    public void shouldCheckWhetherThePeriodIsNegative() {
        Period negative = Period.ofDays(-1);
        assertThat(negative.isNegative(), is(true));
        assertThat(negative.negated().isNegative(), is(false));
    }

    @Test
    public void shouldCreateAModifiedCopyForPeriods() {
        Period p = Period.ofDays(0);
        Period copy = p.withDays(1)
                .withMonths(2)
                .withYears(3);

        assertThat(p.isZero(), is(equalTo(true)));
        assertThat(copy.getYears(), is(equalTo(3)));
        assertThat(copy.getMonths(), is(equalTo(2)));
        assertThat(copy.getDays(), is(equalTo(1)));
    }

    @Test
    public void shouldModifyPeriodsThroughMathOperations() {
        Period p = Period.ofDays(10);
        Period res = p.plusYears(1)
                .minusDays(3)
                .plusMonths(1)
                .multipliedBy(2);

        assertThat(res.toTotalMonths(), is(equalTo(26L)));
        assertThat(res.getYears(), is(equalTo(2)));
        assertThat(res.getMonths(), is(equalTo(2)));
        assertThat(res.getDays(), is(equalTo(14)));
    }
}
