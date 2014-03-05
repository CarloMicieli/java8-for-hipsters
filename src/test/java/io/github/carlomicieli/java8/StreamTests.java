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

import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StreamTests {
    @Test
    public void shouldCreateStreamsWithOnlyOneElement() {
        Stream<Integer> s = Stream.of(1);
        assertThat(s.count(), is(equalTo(1L)));
    }

    @Test
    public void shouldCreateStreamsWithTheSpecifiedElements() {
        Stream<Integer> s = Stream.of(1, 2, 3, 4);
        assertThat(s.toArray(Integer[]::new), is(new Integer[]{1, 2, 3, 4}));
    }

    @Test
    @Ignore
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

}
