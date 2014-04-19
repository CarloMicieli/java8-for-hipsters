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

import java.time.Month;
import java.time.YearMonth;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class YearMonthTests {
    @Test
    public void shouldCreateNewYearMonths() {
        YearMonth june1 = YearMonth.of(2014, 6);
        YearMonth june2 = YearMonth.of(2014, Month.JUNE);
        assertThat(june1, is(equalTo(june2)));
    }

    @Test
    public void shouldGetYearAndMonth() {
        YearMonth june = YearMonth.of(2014, 6);
        assertThat(june.getYear(), is(equalTo(2014)));
        assertThat(june.getMonth(), is(equalTo(Month.JUNE)));
    }

    @Test
    public void shouldGetLengthOfMonths() {
        YearMonth feb = YearMonth.of(2014, 2);
        YearMonth june = YearMonth.of(2014, Month.JUNE);
        assertThat(feb.lengthOfMonth(), is(equalTo(28)));
        assertThat(june.lengthOfMonth(), is(equalTo(30)));
    }
}
