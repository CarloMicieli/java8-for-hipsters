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
package io.github.carlomicieli.java8.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Carlo Micieli
 */
public class MapTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void compute_shouldComputeMappingForTheSpecifiedKey_ifMappingIsPresent() {
        Map<Integer, String> map = numbersMap();

        String res = map.compute(3, (k, v) -> v + " as number is " + k);
        assertThat(res).isEqualTo("three as number is 3");
    }

    @Test
    public void compute_shouldComputeMappingForTheSpecifiedKey_ifMappingIsAbsent() {
        Map<Integer, String> map = numbersMap();

        String res = map.compute(9, (k, v) -> v + " as number is " + k);
        assertThat(res).isEqualTo("null as number is 9");
    }

    @Test
    public void compute_shouldRemoveElementFromMap_ifMappingFunctionReturnsNull() {
        Map<Integer, String> map = numbersMap();

        map.compute(3, (k, v) -> null);

        assertThat(map).doesNotContainKey(3); // it has side-effects!!!
    }

    @Test
    public void computeIfAbsent_shouldComputeMappingValueAndAddToMap() {
        Map<Integer, String> map = numbersMap();

        map.computeIfAbsent(4, k -> "IV");
        map.computeIfAbsent(9, k -> "nine");

        assertThat(map)
                .containsEntry(4, "four")
                .containsEntry(9, "nine");
    }

    @Test
    public void computeIfAbsent_shouldRethrowException_ifKeyIsAbsent() {
        expectedException.expect(RuntimeException.class);

        Map<Integer, String> map = numbersMap();

        Function<Integer, String> mapping = nun -> { throw new RuntimeException(); };

        map.computeIfAbsent(5, mapping);
        map.computeIfAbsent(7, mapping);
    }

    @Test
    public void computeIfPresent_shouldComputeMappingValueAndAddToMap() {
        Map<Integer, String> map = numbersMap();

        map.computeIfPresent(4, (k, v) -> "IV");
        map.computeIfPresent(9, (k, v) -> "nine");

        assertThat(map)
                .containsEntry(4, "IV")
                .doesNotContainKey(9);
    }

    @Test
    public void replace_shouldReplacesTheEntryForTheSpecifiedKey_ifCurrentlyMappedToSomeValue() {
        Map<Integer, String> map = numbersMap();

        map.replace(4, "four", "IV");
        map.replace(5, "V", "five");

        assertThat(map)
                .containsEntry(4, "IV")
                .containsEntry(5, "five");
    }

    @Test
    public void merge_shouldApplyMappingFunction_ifKeyExistsOrAppendNewValueOtherwise() {
        Map<Integer, String> map = numbersMap();

        BiFunction<String, String, String> mapping = (oldVal, newVal) -> oldVal + ", " + newVal;

        map.merge(5, "V", mapping);
        map.merge(6, "six", mapping);

        assertThat(map)
                .containsEntry(5, "five, V")
                .containsEntry(6, "six");
    }

    @Test
    public void getOrDefault_shouldReturnMapValue_ifKeyIsPresent() {
        Map<Integer, String> map = numbersMap();
        assertThat(map.getOrDefault(5, "def")).isEqualTo("five");
    }

    @Test
    public void getOrDefault_shouldReturnDefaultValue_ifKeyIsAbsent() {
        Map<Integer, String> map = numbersMap();
        assertThat(map.getOrDefault(9, "def")).isEqualTo("def");
    }

    @Test
    public void putIfAbsent_shouldReturnValueFromMap_ifAlreadyPresentInMap() {
        Map<Integer, String> map = numbersMap();

        String val = map.putIfAbsent(5, "V");

        assertThat(val).isEqualTo("five");
        assertThat(map).containsEntry(5, "five");
    }

    @Test
    public void putIfAbsent_shouldPutValueIntoMap_ifAbsent() {
        Map<Integer, String> map = numbersMap();

        String val = map.putIfAbsent(9, "nine");

        assertThat(val).isNull();
        assertThat(map).containsEntry(9, "nine");
    }

    private static Map<Integer, String> numbersMap() {
        Map<Integer, String> n = new HashMap<>();
        n.put(1, "one");
        n.put(2, "two");
        n.put(3, "three");
        n.put(4, "four");
        n.put(5, "five");
        return n;
    }
}
