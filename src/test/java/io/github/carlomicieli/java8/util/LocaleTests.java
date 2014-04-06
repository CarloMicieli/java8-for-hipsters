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

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class LocaleTests {

    @Test
    public void shouldParseLanguageRanges() {
        List<Locale.LanguageRange> ranges =
                Locale.LanguageRange.parse("Accept-Language: fr,en;q=0.2,de;q=0.5");

        assertThat(ranges, hasSize(3));

        Map<String, Double> values = ranges.stream()
                .collect(toMap(Locale.LanguageRange::getRange, Locale.LanguageRange::getWeight));
        assertThat(values, hasEntry("fr", 1.0));
        assertThat(values, hasEntry("en", 0.2));
        assertThat(values, hasEntry("de", 0.5));
    }

    @Test
    public void shouldFindBestLocaleMatch() {
        Locale match = Locale.lookup(Locale.LanguageRange.parse("Accept-Language: fr,en;q=0.2,de;q=0.5"),
                Arrays.asList(Locale.getAvailableLocales()));
        assertThat(match, is(equalTo(Locale.FRENCH)));
    }

    @Test
    public void shouldProduceListOfMatchingLocalesFromLanguageRange() {
        List<Locale.LanguageRange> ranges = Stream.of("it", "*-CH")
                .map(Locale.LanguageRange::new)
                .collect(Collectors.toList());
        List<Locale> matches = Locale.filter(ranges,
                Arrays.asList(Locale.getAvailableLocales()));

        assertThat(matches, hasSize(5));
        assertThat(matches, contains(
                new Locale("it"),
                new Locale("it", "CH"),
                new Locale("it", "IT"),
                new Locale("fr", "CH"),
                new Locale("de", "CH")));
    }
}
