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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class TemporalAdjusterTests {

    private final static LocalDate SEPTEMBER_9TH = LocalDate.of(2014, 9, 9);

    @Test
    public void shouldReturnTheThirdSaturdayInSeptember() {
        LocalDate newDate = SEPTEMBER_9TH.with(
                TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.SATURDAY));
        assertThat(newDate, is(equalTo(LocalDate.of(2014, 9, 20))));
    }

    @Test
    public void shouldReturnLocalDateForTwoDaysLater() {
        LocalDate twoDaysLater = SEPTEMBER_9TH.with(
                TemporalAdjusters.ofDateAdjuster(d -> d.plusDays(2)));
        assertThat(twoDaysLater, is(equalTo(LocalDate.of(2014, 9, 11))));
    }

    @Test
    public void shouldReturnTheFirstDayOfMonth() {
        Temporal n = TemporalAdjusters
                .firstDayOfMonth()
                .adjustInto(SEPTEMBER_9TH);
        LocalDate firstDay = LocalDate.from(n);
        assertThat(firstDay, is(equalTo(LocalDate.of(2014, 9, 1))));
    }

    @Test
    public void shouldReturnTheLastDayOfMonth() {
        LocalDate lastDay = SEPTEMBER_9TH.with(TemporalAdjusters.lastDayOfMonth());
        assertThat(lastDay, is(equalTo(LocalDate.of(2014, 9, 30))));
    }

    @Test
    public void shouldReturnTheFirstDayOfYear() {
        LocalDate firstDay = SEPTEMBER_9TH.with(TemporalAdjusters.firstDayOfYear());
        assertThat(firstDay, is(equalTo(LocalDate.of(2014, 1, 1))));
    }

    @Test
    public void shouldReturnTheLastDayOfYear() {
        LocalDate lastDay = SEPTEMBER_9TH.with(TemporalAdjusters.lastDayOfYear());
        assertThat(lastDay, is(equalTo(LocalDate.of(2014, 12, 31))));
    }

    @Test
    public void shouldReturnFirstDayOfNextYear() {
        LocalDate firstDay = SEPTEMBER_9TH.with(TemporalAdjusters.firstDayOfNextYear());
        assertThat(firstDay, is(equalTo(LocalDate.of(2015, 1, 1))));
    }
}
