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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class LocalDateTimeTests {
    private final static LocalDate SEPTEMBER_9TH = LocalDate.of(1981, 9, 9);

    @Test
    public void shouldFormatLocalDateValues() {
        DateTimeFormatter dtf = ofPattern("YYYY-MM-dd").withLocale(Locale.US);
        String dateAsString = SEPTEMBER_9TH.format(dtf);
        assertThat(dateAsString, is(equalTo("1981-09-09")));
        assertThat(SEPTEMBER_9TH.format(DateTimeFormatter.BASIC_ISO_DATE), is("19810909"));
    }

    @Test
    public void shouldParseStringAsLocalDate() {
        LocalDate date = LocalDate.parse("1981-09-09");
        assertThat(date, is(equalTo(SEPTEMBER_9TH)));
    }


}
