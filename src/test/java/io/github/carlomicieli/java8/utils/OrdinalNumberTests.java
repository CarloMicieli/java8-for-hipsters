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
package io.github.carlomicieli.java8.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class OrdinalNumberTests {

    @Test(expected = IllegalArgumentException.class)
    public void of_shouldThrowIllegalArgumentException_WhenProvidedValueIsNegative() {
        OrdinalNumber.of(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_shouldThrowIllegalArgumentException_WhenProvidedValueIsZero() {
        OrdinalNumber.of(0);
    }

    @Test
    public void of_shouldReturn45th_WhenProvidedValueIs45() {
        String n = OrdinalNumber.of(45);
        assertThat(n, is(equalTo("45th")));
    }

    @Test
    public void of_shouldReturn4567th_WhenProvidedValueIs4567() {
        String n = OrdinalNumber.of(4567);
        assertThat(n, is(equalTo("4567th")));
    }

    @Test
    public void of_shouldUseThePrefixForFirst() {
        assertThat(OrdinalNumber.of(1), is(equalTo("1st")));
        assertThat(OrdinalNumber.of(21), is(equalTo("21st")));
        assertThat(OrdinalNumber.of(31), is(equalTo("31st")));
    }

    @Test
    public void of_shouldUseThePrefixForSecond() {
        assertThat(OrdinalNumber.of(2), is(equalTo("2nd")));
        assertThat(OrdinalNumber.of(22), is(equalTo("22nd")));
        assertThat(OrdinalNumber.of(32), is(equalTo("32nd")));
    }

    @Test
    public void of_shouldUseThePrefixForThird() {
        assertThat(OrdinalNumber.of(3), is(equalTo("3rd")));
        assertThat(OrdinalNumber.of(23), is(equalTo("23rd")));
        assertThat(OrdinalNumber.of(33), is(equalTo("33rd")));
    }

    @Test
    public void of_shouldManageOrdinalExceptions() {
        assertThat(OrdinalNumber.of(11), is(equalTo("11th")));
        assertThat(OrdinalNumber.of(12), is(equalTo("12th")));
        assertThat(OrdinalNumber.of(13), is(equalTo("13th")));
    }
}
