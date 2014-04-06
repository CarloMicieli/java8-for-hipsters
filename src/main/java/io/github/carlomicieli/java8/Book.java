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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Comparator;

/**
 * @author Carlo Micieli
 */
public class Book {
    private final String author;
    private final String title;
    private final int pages;
    private final double weight;

    public Book(String author, String title, int pages, double weight) {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.weight = weight;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPagesCount() {
        return pages;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book that = (Book) o;
        return new EqualsBuilder()
                .append(this.author, that.author)
                .append(this.title, that.title)
                .append(this.pages, that.pages)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(31, 7)
                .append(author)
                .append(title)
                .append(pages)
                .hashCode();
    }

    public static final Comparator<Book> BY_PAGE_COUNT_ASC = Comparator.comparing(Book::getPagesCount);

    public static Book longBook() {
        return new Book("JRR Tolkien", "The lord of the rings", 1034, 41.6);
    }

    public static Book shortBook() {
        return new Book("Kurt Vonnegut", "Cat's cradle", 234, 4.2);
    }
}
