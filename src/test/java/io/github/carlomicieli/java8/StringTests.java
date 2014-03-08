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

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StringTests {
    @Test
    public void shouldJoinStrings() {
        String message = String.join("-", "Java", "is", "cool");
        assertThat(message, is(equalTo("Java-is-cool")));
    }

    @Test
    public void shouldJoinStringsFromIterable() {
        Iterable<String> strings = Arrays.asList("Java", "is", "cool");
        String message = String.join("-", strings);
        assertThat(message, is(equalTo("Java-is-cool")));
    }

    @Test(expected = NullPointerException.class)
    public void stringJoin_shouldThrowNullPointerException_WhenDelimiterIsNull() {
        String.join(null, "hello");
    }

    @Test(expected = NullPointerException.class)
    public void stringJoin_shouldThrowNullPointerException_WhenElementsAreNull() {
        List<String> elements = null;
        String.join(", ", elements);
    }

    @Test
    public void shouldJoinStringWithPrefixAndSuffix() {
        StringJoiner sj = new StringJoiner(", ", "<", ">");
        String elements = sj.add("one").add("two").add("three").toString();
        assertThat(elements, is(equalTo("<one, two, three>")));
    }

    @Test
    public void shouldMergeTwoStringJoiners() {
        StringJoiner sj1 = new StringJoiner(", ");
        sj1.add("one");
        StringJoiner sj2 = new StringJoiner(". ");
        sj2.add("two");

        String str = sj1.merge(sj2).toString();
        assertThat(str, is(equalTo("one, two")));
    }
}
