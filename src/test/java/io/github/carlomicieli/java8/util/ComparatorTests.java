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

import io.github.carlomicieli.java8.Book;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class ComparatorTests {

    @Test
    public void shouldSortBooksByAuthorWithNullFirst() {
        Comparator<Book> byAuthorNullFirst = comparing(Book::getAuthor, nullsFirst(naturalOrder()));
        List<String> titlesList = sortedBook(byAuthorNullFirst)
                .map(Book::getAuthor)
                .collect(Collectors.toList());
        assertThat(titlesList, hasSize(5));
        assertThat(titlesList,
                contains(null, "David Foster Wallace", "Graeme Simsion", "JRR Tolkien", "Kurt Vonnegut"));
    }

    @Test
    public void shouldSortBooksByAuthorWithNullLast() {
        Comparator<Book> byAuthorNullLast = comparing(Book::getAuthor, nullsLast(naturalOrder()));
        List<String> titlesList = sortedBook(byAuthorNullLast)
                .map(Book::getAuthor)
                .collect(Collectors.toList());
        assertThat(titlesList, hasSize(5));
        assertThat(titlesList,
                contains("David Foster Wallace", "Graeme Simsion", "JRR Tolkien", "Kurt Vonnegut", null));
    }

    @Test
    public void shouldSortBooksByInversePagesCountAndThenByTitle() {
        List<String> books = books()
                .sorted(comparing(Book::getPagesCount).reversed().thenComparing(Book::getTitle))
                .map(b -> b.getPagesCount() + ", " + b.getTitle())
                .collect(Collectors.toList());

        assertThat(books, contains(
                "1104, Infinite Jest",
                "320, The Hobbit; or, There and Back Again",
                "304, Cat's Cradle",
                "304, The Epic of Gilgamesh",
                "304, The Rosie Project"));
    }

    @Test
    public void shouldSortBooksByPagesCount() {
        Comparator<Book> byPagesCount = comparingInt(Book::getPagesCount);
        List<Integer> pages = sortedBook(byPagesCount)
                .map(Book::getPagesCount)
                .collect(Collectors.toList());
        assertThat(pages, contains(304, 304, 304, 320, 1104));
    }

    @Test
    public void shouldSortBooksByWeight() {
        Comparator<Book> byWeight = comparingDouble(Book::getWeight);
        List<Double> pages = sortedBook(byWeight)
                .mapToDouble(Book::getWeight)
                .boxed()
                .collect(Collectors.toList());
        assertThat(pages, contains(3.9, 4.2, 5.4, 8.5, 41.6));
    }

    private static Stream<Book> sortedBook(Comparator<Book> cmp) {
        return books().sorted(cmp);
    }

    private static Stream<Book> books() {
        return BOOKS.stream();
    }

    private static final List<Book> BOOKS = initBooks();

    private static List<Book> initBooks() {
        return Arrays.asList(
                new Book("David Foster Wallace", "Infinite Jest", 1104, 41.6),
                new Book("Kurt Vonnegut", "Cat's Cradle", 304, 4.2),
                new Book("JRR Tolkien", "The Hobbit; or, There and Back Again", 320, 5.4),
                new Book("Graeme Simsion", "The Rosie Project", 304, 3.9),
                new Book(null, "The Epic of Gilgamesh", 304, 8.5)
        );
    }
}
