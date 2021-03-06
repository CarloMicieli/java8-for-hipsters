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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class OptionalTests {

    private Optional<String> some = Optional.of("found");
    private Optional<String> none = Optional.empty();

    @Test
    public void shouldBuildOptionalValues() {
        Optional<String> some = Optional.ofNullable("found");
        Optional<String> none = Optional.ofNullable(null);
        assertThat(some.isPresent(), is(equalTo(true)));
        assertThat(none.isPresent(), is(equalTo(false)));
    }

    @Test
    public void shouldReturnTheWrappedValueIfNotNull() {
        assertThat(some.get(), is(equalTo("found")));
        assertThat(some.isPresent(), is(true));
    }

    @Test
    public void shouldCheckWhetherWrappedValueExists() {
        assertThat(none.isPresent(), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowsNPEWhenTheWrappedValueIsNull() {
        Optional.of(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowsNoSuchElementWhenItsEmpty() {
        none.get();
    }

    @Test
    public void shouldConsumeTheCorrectValueOrProducesAnAlternative() {
        String v1 = some.orElse("not found");
        String v2 = none.orElse("not found");

        assertThat(v1, is(equalTo("found")));
        assertThat(v2, is(equalTo("not found")));
    }

    @Test
    public void shouldApplyAFunctionOnlyWhenAValueExists() {
        List<String> results = new ArrayList<>();

        some.ifPresent(s -> results.add("Added " + s));
        none.ifPresent(results::add);

        assertThat(results, hasSize(1));
        assertThat(results, hasItem("Added found"));
    }

    @Test
    public void shouldCallFunctionsToProduceValueWhenEmpty() {
        String value = none.orElseGet(() -> "default value");
        assertThat(value, is(equalTo("default value")));
    }

    @Test
    public void shouldMapTheWrappedValue() {
        Optional<String> upperSome = some.map(String::toUpperCase);
        Optional<String> upperNone = none.map(String::toUpperCase);

        assertThat(upperSome.get(), is(equalTo("FOUND")));
        assertThat(upperNone.isPresent(), is(false));
    }

    @Test
    public void shouldFlatMapTheWrapperValue() {
        Function<String, Optional<Integer>> fun = s -> Optional.ofNullable(s.length());

        Optional<Integer> someLen = some.flatMap(fun);
        Optional<Integer> noneLen = none.flatMap(fun);

        assertThat(someLen.get(), is(equalTo(5)));
        assertThat(noneLen.isPresent(), is(equalTo(false)));
    }

    @Test
    public void shouldApplyMoreFlatMapFunctions() {
        Optional<Integer> someLen = Optional.of(1000L)
                .flatMap(id -> Optional.ofNullable(fakeNameLookup(id)))
                .flatMap(name -> Optional.ofNullable(name.length()));
        assertThat(someLen.get(), is(equalTo(10)));
    }

    @Test
    public void shouldApplySafelyFlatMapFunctionsWhenIsEmpty() {
        Optional<Integer> noneLen = Optional.of(0L)
                .flatMap(id -> Optional.ofNullable(fakeNameLookup(id)))
                .flatMap(name -> Optional.ofNullable(name.length()));
        assertThat(noneLen.isPresent(), is(false));
    }

    @Test
    public void shouldFilterUsingPredicates() {
        Optional<String> filterSome = some.filter(s -> s.length() > 4);
        Optional<String> filterNone = some.filter(s -> s.length() > 10);

        assertThat(filterSome.get(), is("found"));
        assertThat(filterNone.isPresent(), is(false));
    }

    private String fakeNameLookup(Long id) {
        return id > 0 ? "Name(" + id + ")" : null;
    }
}
