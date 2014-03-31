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
package io.github.carlomicieli.java8.functions;

import io.github.carlomicieli.java8.Book;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class FunctionTests {

    @Test
    public void toIntBiFunction_shouldAcceptTwoOperands_AndProduces_AnIntResult() {
        ToIntBiFunction<Book, Book> pagesF = (a, b) -> a.getPagesCount() + b.getPagesCount();
        int totalPages = pagesF.applyAsInt(Book.longBook(), Book.shortBook());
        assertThat(totalPages, is(equalTo(1268)));
    }

    @Test
    public void shouldComposeFunctions_with_andThen() {
        Function<String, Integer> charsNum = s -> s.replace(" ", "").length();
        Function<Integer, Integer> doubled = n -> n * 2;

        // first applies this function to its input (charsNum),
        // and then applies the after function to the result (doubled)
        Function<String, Integer> composed = charsNum.andThen(doubled);
        assertThat(composed.apply("hello world"), is(equalTo(20)));
    }

    @Test
    public void shouldComposeFunctions_with_compose() {
        Function<String, Integer> charsNum = s -> s.replace(" ", "").length();
        Function<Integer, Integer> doubled = n -> n * 2;

        // first applies the before function to its input (charsNum),
        // and then applies this function to the result (doubled)
        Function<String, Integer> composed = doubled.compose(charsNum);
        assertThat(composed.apply("hello world"), is(equalTo(20)));
    }
}
