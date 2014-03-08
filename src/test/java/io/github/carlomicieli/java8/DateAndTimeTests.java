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
package io.github.carlomicieli.java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class DateAndTimeTests {
    @Test
    public void shouldObtainLocalDateFromYearMonthAndDay() {
        LocalDate september9th = LocalDate.of(1981, 9, 9);
        assertThat(september9th.getDayOfMonth(), is(equalTo(9)));
        assertThat(september9th.getMonth(), is(equalTo(Month.SEPTEMBER)));
        assertThat(september9th.getYear(), is(equalTo(1981)));
    }

    @Test
    public void shouldObtainLocalDateFromYearAndDayOfYear() {
        LocalDate september9th = LocalDate.ofYearDay(1981, 252);
        assertThat(september9th, is(equalTo(LocalDate.of(1981, Month.SEPTEMBER, 9))));
        assertThat(september9th.getDayOfYear(), is(equalTo(252)));
    }

    @Test
    public void shouldObtainLocalTimeFromHourAndMinutes() {
        LocalTime time = LocalTime.of(12, 30);
        assertThat(time.getHour(), is(equalTo(12)));
        assertThat(time.getMinute(), is(equalTo(30)));
        assertThat(time.getSecond(), is(equalTo(0)));
    }

    @Test
    public void shouldObtainLocalTimeFromSecondOfDay() {
        LocalTime time = LocalTime.ofSecondOfDay(12_345);
        assertThat(time, is(LocalTime.of(03, 25, 45)));
    }
}
