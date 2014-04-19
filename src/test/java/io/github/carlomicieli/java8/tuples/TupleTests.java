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
package io.github.carlomicieli.java8.tuples;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class TupleTests {

    @Test
    public void shouldCreateNewPairs() {
        Pair<Integer, String> pair = Tuple.of(1, "one");
        assertThat(pair.first(), is(equalTo(1)));
        assertThat(pair.second(), is(equalTo("one")));
    }

    @Test
    public void shouldReturnStringRepresentationsForPairs() {
        Pair<Integer, String> pair = Tuple.of(1, "one");
        assertThat(pair.toString(), is("Pair(1, one)"));
    }

    @Test
    public void shouldCreateNewTriples() {
        Triple<Integer, String, Double> triple = Tuple.of(1, "two", 3.3);
        assertThat(triple.first(), is(equalTo(1)));
        assertThat(triple.second(), is(equalTo("two")));
        assertThat(triple.third(), is(equalTo(3.3)));
    }

    @Test
    public void shouldReturnStringRepresentationsForTriples() {
        Triple<Integer, String, Double> triple = Tuple.of(1, "two", 3.3);
        assertThat(triple.toString(), is("Triple(1, two, 3.3)"));
    }
}
