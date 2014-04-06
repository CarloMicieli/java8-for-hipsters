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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class YearTests {

    final static Year YEAR_2014 = Year.of(2014);

    @Test
    public void shouldCombineYearsAndDaysOfYear() {
        assertThat(YEAR_2014.atDay(100), is(LocalDate.of(2014, 4, 10)));
    }

    @Test
    public void shouldCombineYearWithMonth() {
        YearMonth month = YEAR_2014.atMonth(5);
        YearMonth month2 = YEAR_2014.atMonth(Month.MAY);
        assertThat(month.getMonth(), is(equalTo(Month.MAY)));
        assertThat(month2.getMonth(), is(equalTo(Month.MAY)));
    }

    @Test
    public void shouldCombineYearWithMonthDay() {
        LocalDate date = YEAR_2014.atMonthDay(MonthDay.of(5, 15));
        assertThat(date, is(equalTo(LocalDate.of(2014, 5, 15))));
    }

    @Test
    public void shouldCheckIfYearIsAfterTheSpecifiedYear() {
        Year y1982 = Year.of(1982);
        Year y2006 = Year.of(2006);

        assertThat(y1982.isAfter(y2006), is(equalTo(false)));
        assertThat(y2006.isAfter(y1982), is(equalTo(true)));
    }

    @Test
    public void shouldCheckIfYearIsBeforeTheSpecifiedYear() {
        Year y1982 = Year.of(1982);
        Year y2006 = Year.of(2006);

        assertThat(y1982.isBefore(y2006), is(equalTo(true)));
        assertThat(y2006.isBefore(y1982), is(equalTo(false)));
    }

    @Test
    public void shouldCheckIfLeapYear() {
        Year y2006 = Year.of(2006);
        assertThat(y2006.isLeap(), is(equalTo(false)));
        assertThat(Year.isLeap(2004), is(equalTo(true)));
    }
}
