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
package io.github.carlomicieli.java8.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StreamTests {

    @Test
    public void shouldCheckWhetherAllElementsMatchThePredicate() {
        assertThat(numbers().allMatch(n -> n < 100), is(equalTo(true)));
        assertThat(numbers().allMatch(n -> n % 2 == 0), is(equalTo(false)));
    }

    @Test
    public void shouldCheckWhetherAnyElementMatchesThePredicate() {
        assertThat(numbers().anyMatch(n -> n > 100), is(equalTo(false)));
        assertThat(numbers().anyMatch(n -> n % 2 == 0), is(equalTo(true)));
    }

    private Stream<Integer> numbers() {
        return Stream.of(1, 45, 23, 22, 55, 66, 80);
    }

    @Test
    public void shouldConcatTwoStreams() {
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4);
        Stream<Integer> s2 = Stream.of(5, 6, 7, 8);

        Stream<Integer> s = Stream.concat(s1, s2);

        List<Integer> values = s.collect(Collectors.toList());
        assertThat(values.size(), is(equalTo(8)));
        assertThat(values, contains(1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void shouldCreateStreamsFromArrays() {
        String[] words = new String[] {
            "one", "two", "three", "four", "five"
        };
        Stream<String> wordsStream = Arrays.stream(words);

        assertThat(wordsStream.toArray(String[]::new), is(equalTo(words)));
    }

    @Test
    public void shouldCreateStreamsWithOnlyOneElement() {
        Stream<Integer> s = Stream.of(1);
        assertThat(s.count(), is(equalTo(1L)));
    }

    @Test
    public void shouldCreateStreamsWithTheSpecifiedElements() {
        Stream<Integer> s = Stream.of(1, 2, 3, 4);
        List<Integer> elems = s.collect(Collectors.toList());
        assertThat(elems, contains(1, 2, 3, 4));
    }

    @Test
    public void shouldCreateArraysWithStreamElements() {
        Integer[] arr = Stream.of(1, 2, 3, 4).toArray(Integer[]::new);
        assertThat(arr, is(new Integer[]{1, 2, 3, 4}));
    }

    @Test
    public void shouldGenerateInfiniteStreams() {
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);
        int val = infiniteStream
                .skip(100)
                .limit(1)
                .findFirst().orElse(-1);
        assertThat(val, is(equalTo(100)));
    }

    @Test
    public void shouldApplyFunctionToStreamElements() {
        String[] upperStrings = Stream.of("home", "sweet", "home")
                .map(String::toUpperCase)
                .toArray(String[]::new);

        assertThat(upperStrings, is(new String[]{"HOME", "SWEET", "HOME"}));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowsExceptionConsumingStreamsAfterATerminalOperation() {
        Stream<Integer> s = Stream.of(1, 4, 43, 56, 59);
        s.count();

        // throws java.lang.IllegalStateException("stream has already been operated upon or closed")
        s.distinct();
    }

    @Test
    public void shouldFilterOutNullValuesFromStreams() {
        List<String> names = Arrays.asList("one", null, null, "four", "five");
        long nullCount = names.stream()
                .filter(Objects::isNull)
                .count();
        assertThat(nullCount, is(equalTo(2L)));
    }

    @Test
    public void shouldFilterOutNonNullValuesFromStreams() {
        List<String> names = Arrays.asList("one", null, null, "four", "five");

        long nonNullCount = names.stream()
                .filter(Objects::nonNull)
                .count();

        assertThat(nonNullCount, is(equalTo(3L)));
    }

    @Test
    public void shouldReturnSummaryStatistics() {
        Stream<String> wordsStream = Stream.of("one", "two", "three", "four", "five", "six", "seven");

        IntSummaryStatistics stats = wordsStream.collect(
                Collectors.summarizingInt(String::length));

        assertThat(stats.getMin(), is(equalTo(3)));
        assertThat(stats.getMax(), is(equalTo(5)));
        assertThat(stats.getCount(), is(equalTo(7L)));
        assertThat(stats.getSum(), is(equalTo(27L)));
        assertThat(stats.getAverage(), is(closeTo(3.857, 0.001)));
    }

}
