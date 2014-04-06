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

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class DayOfWeekTests {

    private final LocalDate SEPTEMBER_9TH = LocalDate.of(1981, 9, 9);

    @Test
    public void shouldObtainInformationAboutDayOfWeek() {
        DayOfWeek day = SEPTEMBER_9TH.getDayOfWeek();
        assertThat(day, is(equalTo(WEDNESDAY)));
        assertThat(day.getValue(), is(equalTo(3)));
        assertThat(day.name(), is(equalTo("WEDNESDAY")));
    }

    @Test
    public void shouldGetLocalizedDisplayNameForDayOfWeeks() {
        String fullEnglish = SATURDAY.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String narrowFrench = SATURDAY.getDisplayName(TextStyle.NARROW, Locale.FRENCH);
        String shortGerman = SATURDAY.getDisplayName(TextStyle.SHORT, Locale.GERMAN);

        assertThat(fullEnglish, is(equalTo("Saturday")));
        assertThat(narrowFrench, is(equalTo("S")));
        assertThat(shortGerman, is(equalTo("Sa")));
    }

    @Test
    public void shouldGetTheDayOfWeekNumber() {
        assertThat(SATURDAY.getValue(), is(equalTo(6)));
    }

    @Test
    public void shouldGetTheDayOfWeek_ThatIsTheSpecifiedNumberOfDaysBefore() {
        DayOfWeek day1 = SATURDAY.minus(3L);
        DayOfWeek day2 = SATURDAY.minus(-4L);
        assertThat(day1, is(equalTo(WEDNESDAY)));
        assertThat(day2, is(equalTo(WEDNESDAY)));
    }

    @Test
    public void shouldObtainInstanceOfDayOfWeekFromAnIntValue() {
        DayOfWeek day = DayOfWeek.of(3);
        assertThat(day, is(equalTo(WEDNESDAY)));
    }

    @Test
    public void shouldObtainInstanceOfDayOfWeekFromName() {
        DayOfWeek day = DayOfWeek.valueOf("WEDNESDAY");
        assertThat(day, is(equalTo(WEDNESDAY)));
    }

    @Test(expected = DateTimeException.class)
    public void shouldThrowDateTimeExceptionWhenIntValueIsInvalid() {
        DayOfWeek.of(8);
    }
}
