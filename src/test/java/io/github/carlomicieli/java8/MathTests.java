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

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class MathTests {

    @Test
    public void double_shouldReturnTheNumberOfBytesUsed() {
        assertThat(Double.BYTES, is(equalTo(8)));
    }

    @Test
    public void double_shouldCheckWhetherNumbersAreInfinite() {
        assertThat(Double.isFinite(100.0), is(equalTo(true)));
        assertThat(Double.isFinite(Double.NaN), is(equalTo(false)));
        assertThat(Double.isFinite(Double.NEGATIVE_INFINITY), is(equalTo(false)));
        assertThat(Double.isFinite(Double.POSITIVE_INFINITY), is(equalTo(false)));
    }

    @Test
    public void double_shouldPerformBasicOperations() {
        double a = 1938475638467.98;
        double b = 736455277.14;

        assertThat(Double.sum(a, b), is(closeTo(1.9392120937451199E12, 0.1)));
        assertThat(Double.max(a, b), is(closeTo(a, 0.01)));
        assertThat(Double.min(a, b), is(closeTo(b, 0.01)));
    }

    @Test
    public void integer_shouldReturnTheNumberOfBytesUsed() {
        assertThat(Integer.BYTES, is(equalTo(4)));
    }

    @Test
    public void integer_shouldReturnStringRepresentationForUnsigned() {
        int a = -1234567;
        int b = 1234567;

        assertThat(Integer.toUnsignedString(a, 10), is(equalTo("4293732729")));
        assertThat(Integer.toUnsignedString(b), is(equalTo("1234567")));
    }

    @Test
    public void integer_shouldParseStringAsUnsignedNumbers() {
        String a = "1234567";
        String b = "0101111010";

        assertThat(Integer.parseUnsignedInt(a), is(equalTo(1234567)));
        assertThat(Integer.parseUnsignedInt(b, 2), is(equalTo(378)));
    }

    @Test(expected = NumberFormatException.class)
    public void integer_shouldThrowNumberFormatException_WhenParsingAnInvalidValue() {
        Integer.parseUnsignedInt("Z");
    }
}
