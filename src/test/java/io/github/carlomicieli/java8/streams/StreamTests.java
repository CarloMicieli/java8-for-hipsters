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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StreamTests {

    private Stream<Integer> numbers() {
        return Stream.of(1, 45, 23, 22, 55, 66, 80);
    }

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

    @Test
    public void shouldConcatTwoStreams() {
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4);
        Stream<Integer> s2 = Stream.of(5, 6, 7, 8);

        Stream<Integer> s = Stream.concat(s1, s2);

        List<Integer> l = s.collect(Collectors.toList());
        assertThat(l.size(), is(equalTo(8)));
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
        Stream<Integer> s = Stream.iterate(0, n -> n + 1);
        Optional<Integer> val = s.skip(100).limit(1).findFirst();
        int n = val.orElse(-1);
        assertThat(n, is(equalTo(100)));
    }

    @Test
    public void shouldApplyFunctionToStreamElements() {
        Stream<String> s = Stream.of("home", "sweet", "home");
        Stream<String> s2 = s.map(String::toUpperCase);

        assertThat(s2.toArray(String[]::new), is(new String[]{"HOME", "SWEET", "HOME"}));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowsExceptionConsumingStreamsAfterATerminalOperation() {
        Stream<Integer> s = Stream.of(1, 4, 43, 56, 59);
        s.count();

        // throws java.lang.IllegalStateException("stream has already been operated upon or closed")
        s.distinct();
    }

}
