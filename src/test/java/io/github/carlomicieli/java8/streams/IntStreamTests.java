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
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class IntStreamTests {

    @Test(expected = IllegalStateException.class)
    public void terminalOperations_shouldCloseIntStreams_anyFurtherOperationShouldThrowException() {
        IntStream intStream = IntStream.of(1);
        intStream.count();
        intStream.max();    // <-- stream has been closed by count()
    }

    @Test
    public void builder_shouldBuildNewIntStreams() {
        IntStream intStream = IntStream.builder().add(1).add(2).add(3).build();

        List<Integer> values = valuesOf(intStream);
        assertThat(values, hasSize(3));
        assertThat(values, hasItems(1, 2, 3));
    }

    @Test(expected = IllegalStateException.class)
    public void builder_shouldThrowIllegalStateException_modifyingAlreadyBuildIntStreams() {
        IntStream.Builder builder = IntStream.builder().add(1).add(2);
        builder.build();
        builder.add(5);
    }

    @Test
    public void range_shouldReturnsAnOrderedSequenceWithIncrementOf1() {
        IntStream s = IntStream.range(1, 10);

        List<Integer> values = valuesOf(s);
        assertThat(values, hasSize(9));
        assertThat(values, hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void concat_shouldConcatTwoIntStreams() {
        IntStream intStream1 = IntStream.builder().add(1).add(3).add(5).build();
        IntStream intStream2 = IntStream.builder().add(2).add(4).add(6).build();

        IntStream intStream = IntStream.concat(intStream1, intStream2);

        List<Integer> values = valuesOf(intStream);
        assertThat(values, hasSize(6));
        assertThat(values, hasItems(1, 3, 5, 2, 4, 6));
    }

    @Test
    public void rangeClosed_shouldReturnsAnOrderedAndClosedSequenceWithIncrementOf1() {
        IntStream s = IntStream.rangeClosed(1, 10);

        List<Integer> values = valuesOf(s);
        assertThat(values, hasSize(10));
        assertThat(values, hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void generate_shouldReturnInfiniteSequenceGeneratedByTheProvidedSupplier() {
        IntSupplier onesSupplier = () -> 1;

        IntStream infinite = IntStream.generate(onesSupplier);
        int sum = infinite.limit(100L).sum();
        assertThat(sum, is(equalTo(100)));
    }

    @Test
    public void empty_shouldGenerateEmptyIntStreams() {
        IntStream empty = IntStream.empty();
        assertThat(empty.count(), is(equalTo(0L)));
    }

    @Test
    public void iterate_shouldGenerateIntStreams_producedByIterativeFunctions() {
        IntStream powersOf2 = IntStream.iterate(2, n -> n * 2).limit(10);

        List<Integer> values = valuesOf(powersOf2);
        assertThat(values, hasSize(10));
    }

    @Test
    public void of_shouldGenerateIntStreams_withTheProvidedValues() {
        IntStream streamOf2and5 = IntStream.of(2, 5);
        assertThat(streamOf2and5.count(), is(equalTo(2L)));
    }

    @Test
    public void allMatch_shouldCheckWhetherAllElements_matchTheProvidedPredicate() {
        boolean match = IntStream
                .iterate(2, n -> n * 2)
                .limit(10)
                .allMatch(n -> n % 2 == 0);
        assertThat(match, is(true));
    }

    @Test
    public void noneMatch_shouldCheckWhetherNoElement_matchTheProvidedPredicate() {
        boolean noneMatch = IntStream
                .iterate(2, n -> n * 2)
                .limit(10)
                .noneMatch(n -> n % 2 != 0);
        assertThat(noneMatch, is(true));
    }

    private static List<Integer> valuesOf(IntStream is) {
        return is.boxed().collect(Collectors.toList());
    }
}
