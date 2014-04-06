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

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class CollectionsTests {

    @Test(expected = UnsupportedOperationException.class)
    public void unmodifiableNavigableSet_shouldThrowUnsupportedOperationException_WhenElementIsAdded() {
        NavigableSet<String> set = navigableSet("one", "two", "three");
        NavigableSet<String> set2 = Collections.unmodifiableNavigableSet(set);
        set2.add("one");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unmodifiableNavigableMap_shouldThrowUnsupportedOperationException_WhenElementIsAdded() {
        NavigableMap<Integer, String> map = navigableMap();
        NavigableMap<Integer, String> map2 = Collections.unmodifiableNavigableMap(map);
        map2.put(1, "one");
    }

    @Test(expected = ClassCastException.class)
    public void checkedQueue_shouldThrowClassCastException_whenValueCannotBeCast() {
        Queue<String> q = queue("one", "two", "three");
        Queue<String> q2 = Collections.checkedQueue(q, String.class);
        Object val = 10;
        q2.add((String)val);
    }

    @Test(expected = ClassCastException.class)
    public void checkedNavigableMap_shouldThrowClassCastException_whenValueCannotBeCast() {
        NavigableMap<Integer, String> map = new TreeMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Object val = 10;
        NavigableMap<Integer, String> map2 = Collections.checkedNavigableMap(map, Integer.class, String.class);
        map2.put(10, (String)val);
    }

    @Test
    public void emptySortedSet_shouldReturnAnEmptySortedSet() {
        SortedSet<String> empty = Collections.emptySortedSet();
        assertThat(empty.size(), is(equalTo(0)));
    }

    @Test
    public void emptyNavigableSet_shouldReturnAnEmptyNavigableSet() {
        NavigableSet<String> empty = Collections.emptyNavigableSet();
        assertThat(empty.size(), is(equalTo(0)));
    }

    @Test
    public void emptySortedMap_shouldReturnAnEmptySortedMap() {
        SortedMap<String, String> empty = Collections.emptySortedMap();
        assertThat(empty.size(), is(equalTo(0)));
    }

    @Test
    public void emptyNavigableMap_shouldReturnAnEmptyNavigableMap() {
        NavigableMap<String, String> empty = Collections.emptyNavigableMap();
        assertThat(empty.size(), is(equalTo(0)));
    }

    private static Queue<String> queue(String... values) {
        return Stream.of(values)
               .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private static NavigableSet<String> navigableSet(String... values) {
        NavigableSet<String> set = new TreeSet<>();
        Stream.of(values).forEach(set::add);
        return set;
    }

    private static NavigableMap<Integer, String> navigableMap() {
        NavigableMap<Integer, String> map = new TreeMap<>();
        map.put(1, "one");
        map.put(2, "two");
        return map;
    }
}
