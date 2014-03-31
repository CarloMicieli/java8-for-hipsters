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

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static io.github.carlomicieli.java8.Book.longBook;
import static io.github.carlomicieli.java8.Book.shortBook;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class BinaryOperatorTests {
    @Test
    public void binaryOperator_isASpecialFunction_WhereTheOperandsAndResultHaveTheSameType() {
        BinaryOperator<Book> opFirst = (b1, b2) -> b1;
        Book first = opFirst.apply(shortBook(), longBook());
        assertThat(first, is(equalTo(shortBook())));
    }

    @Test
    public void binaryOperator_shouldReturnTheLesserOfTwoElementsAccordingComparator() {
        BinaryOperator<Book> minOp = BinaryOperator.minBy(Comparator.comparing(Book::getPagesCount));
        Book shorter = minOp.apply(shortBook(), longBook());
        assertThat(shorter, is(equalTo(shortBook())));
    }

    @Test
    public void binaryOperator_shouldReturnTheGreaterOfTwoElementsAccordingComparator() {
        BinaryOperator<Book> greatOp = BinaryOperator.maxBy(Comparator.comparing(Book::getPagesCount));
        Book greater = greatOp.apply(shortBook(), longBook());
        assertThat(greater, is(equalTo(longBook())));
    }

    @Test
    public void binaryOperator_shouldApplyTheOperator_AndThen_a_Function() {
        BinaryOperator<Book> greatOp = BinaryOperator.maxBy(Comparator.comparing(Book::getPagesCount));
        Function<Book, Boolean> moreThan1000pages = b -> b.getPagesCount() > 1000;

        BiFunction<Book, Book, Boolean> bookLongerThan1000pages = greatOp.andThen(moreThan1000pages);

        boolean longestThan1000pages = bookLongerThan1000pages.apply(shortBook(), longBook());
        assertThat(longestThan1000pages, is(true));
    }

    @Test(expected = NullPointerException.class)
    public void binaryOperator_shouldThrow_NullPointerException_WhenTheAfterFunction_is_null() {
        BinaryOperator<Book> opFirst = (b1, b2) -> b1;
        opFirst.andThen(null);
    }
}
