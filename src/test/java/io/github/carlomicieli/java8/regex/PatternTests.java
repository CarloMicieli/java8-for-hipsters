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
package io.github.carlomicieli.java8.regex;

import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PatternTests {

    private final static String ONE_RING_TO_RULE_THEM_ALL =
            "Three Rings for the Elven-kings under the sky, " +
            "Seven for the Dwarf-lords in their halls of stone, " +
            "Nine for Mortal Men doomed to die, " +
            "One for the Dark Lord on his dark throne " +
            "In the Land of Mordor where the Shadows lie. " +
            "One Ring to rule them all, One Ring to find them, " +
            "One Ring to bring them all and in the darkness bind them " +
            "In the Land of Mordor where the Shadows lie.";

    @Test
    public void shouldUsePatternsToSplitInputAsStreams() {
        Pattern spacesPattern = Pattern.compile("\\s+");

        List<String> wordsList = spacesPattern
                .splitAsStream(ONE_RING_TO_RULE_THEM_ALL)
                .collect(Collectors.toList());

        assertThat(wordsList, hasSize(74));
    }

    @Test
    public void shouldRemoveTrailingEmptyStringsInTheResultingStream() {
        String str = "      ,one   ,two,  three   ";

        // should match (0 or more spaces) + a comma + (0 or more spaces)
        Pattern spacesPattern = Pattern.compile("\\s*,\\s*");
        List<String> words = spacesPattern
                .splitAsStream(str)
                .collect(Collectors.toList());
        assertThat(words, hasSize(4));
        assertThat(words, contains("", "one", "two", "three   "));
    }

    @Test
    public void shouldReturnInputStringIfPatternDoesNotMatchAnything() {
        String str = "a,b,c";
        String first = Pattern.compile("\\s+")
                .splitAsStream(str)
                .findFirst().orElse(null);
        assertThat(first, is(equalTo(str)));
    }

    @Test
    public void shouldFilterStreamsUsingPatternAsPredicates() {
        Pattern spacesPattern = Pattern.compile("\\s+");

        long count = spacesPattern.splitAsStream(ONE_RING_TO_RULE_THEM_ALL)
                .filter(Pattern.compile("Mordor").asPredicate())
                .count();
        assertThat(count, is(equalTo(2L)));
    }
}
