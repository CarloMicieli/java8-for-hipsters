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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class DateAndTimeTests {
    private final static LocalDate SEPTEMBER_9TH = LocalDate.of(1981, 9, 9);

    @Test
    public void shouldCreateNewLocalDates() {
        LocalDate d1 = LocalDate.of(1981, 9, 9);
        LocalDate d2 = LocalDate.of(1981, Month.SEPTEMBER, 9);
        assertThat(d1, is(equalTo(d2)));
    }

    @Test
    public void shouldGetYearMonthAndDayFromLocalDate() {
        assertThat(SEPTEMBER_9TH.getDayOfMonth(), is(equalTo(9)));
        assertThat(SEPTEMBER_9TH.getMonth(), is(equalTo(Month.SEPTEMBER)));
        assertThat(SEPTEMBER_9TH.getYear(), is(equalTo(1981)));
    }

    @Test
    public void shouldGetTheDayOfWeekFromLocalDate() {
        assertThat(SEPTEMBER_9TH.getDayOfWeek(), is(equalTo(DayOfWeek.WEDNESDAY)));
    }

    @Test
    public void shouldGetTheDayOfYearFromLocalDate() {
        assertThat(SEPTEMBER_9TH.getDayOfYear(), is(equalTo(252)));
    }

    @Test
    public void shouldCreateNewLocalDateFromYearAndDayOfYear() {
        LocalDate september9th = LocalDate.ofYearDay(1981, 252);
        assertThat(september9th, is(equalTo(SEPTEMBER_9TH)));
        assertThat(september9th.getDayOfYear(), is(equalTo(252)));
    }

    @Test
    public void shouldCheckWhetherYearIsLeapFromLocalDate() {
        LocalDate leapYear = LocalDate.of(2012, 2, 1);
        LocalDate notLeapYear = LocalDate.of(2013, 2, 1);

        assertThat(leapYear.getYear(), is(equalTo(2012)));
        assertThat(leapYear.isLeapYear(), is(true));
        assertThat(leapYear.lengthOfYear(), is(equalTo(366)));

        assertThat(notLeapYear.isLeapYear(), is(false));
        assertThat(notLeapYear.lengthOfYear(), is(equalTo(365)));
    }

    @Test
    public void shouldCreateLocalTimeFromHourAndMinutes() {
        LocalTime time = LocalTime.of(12, 30);
        assertThat(time.getHour(), is(equalTo(12)));
        assertThat(time.getMinute(), is(equalTo(30)));
        assertThat(time.getSecond(), is(equalTo(0)));
    }

    @Test
    public void shouldCreateLocalTimeFromHoursMinutesAndSeconds() {
        LocalTime time = LocalTime.of(12, 30, 15);
        assertThat(time.getHour(), is(equalTo(12)));
        assertThat(time.getMinute(), is(equalTo(30)));
        assertThat(time.getSecond(), is(equalTo(15)));
    }

    @Test
    public void shouldCreateLocalTimeFromSecondOfDay() {
        LocalTime time = LocalTime.ofSecondOfDay(12_345);
        assertThat(time, is(LocalTime.of(3, 25, 45)));
    }

    @Test
    public void shouldCreateLocalDateTimeFromLocalTime() {
        LocalDateTime ldt = LocalTime.NOON.atDate(LocalDate.of(1981, 9, 9));
        assertThat(ldt.toLocalDate(), is(equalTo(SEPTEMBER_9TH)));
        assertThat(ldt.toLocalTime(), is(equalTo(LocalTime.NOON)));
    }

    @Test
    public void shouldCreateLocalDateTimeFromLocalDate() {
        LocalDateTime ldt = SEPTEMBER_9TH.atTime(LocalTime.NOON);
        assertThat(ldt.toLocalDate(), is(equalTo(SEPTEMBER_9TH)));
        assertThat(ldt.toLocalTime(), is(equalTo(LocalTime.NOON)));
    }
}
