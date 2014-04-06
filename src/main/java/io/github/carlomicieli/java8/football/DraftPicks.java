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
package io.github.carlomicieli.java8.football;

import com.google.gson.reflect.TypeToken;
import io.github.carlomicieli.java8.helpers.Loader;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

/**
 * @author Carlo Micieli
 */
public class DraftPicks {
    private static final String DRAFTS_DATA_JSON = "/data/draft.json";
    private static final List<DraftPick> data = draftPicksList();

    public static Stream<DraftPick> stream() {
        return data.stream();
    }

    public static List<DraftPick> draftRoundPicks(int year, int round) {
        validateRound(round);
        return draftRounds(year).get(round);
    }

    private static void validateRound(int round) {
        if (rangeClosed(1, 8).noneMatch(r -> r == round)) {
            throw new IllegalArgumentException("Round number " + round + " is invalid.");
        }
    }

    public static Map<Integer, List<DraftPick>> draftRounds(int year) {
        return filterByYear(stream(), year)
                .sorted(comparing(DraftPick::getOverall))
                .collect(groupingBy(DraftPick::getRound));
    }

    public static Map<String, Long> picksByPosition(int year) {
        return filterByYear(stream(), year)
                .collect(groupingBy(DraftPick::getPosition, counting()));
    }

    public static List<DraftPick> picksByCollege(String college) {
        return stream()
                .filter(p -> p.getCollege().equals(college))
                .sorted(comparing(DraftPick::getYear).thenComparing(DraftPick::getOverall))
                .collect(toList());
    }

    public static List<DraftPick> picksByCollegeAndRound(String college, int round) {
        validateRound(round);
        return stream()
                .filter(p -> p.getCollege().equals(college))
                .filter(p -> p.getRound() == round)
                .sorted(comparing(DraftPick::getYear).thenComparing(DraftPick::getOverall))
                .collect(toList());
    }

    private static List<DraftPick> draftPicksList() {
        Loader l = new Loader(DRAFTS_DATA_JSON);
        return l.load(new TypeToken<List<DraftPick>>() {}.getType());
    }

    private static Stream<DraftPick> filterByYear(Stream<DraftPick> stream, int year) {
        return stream.filter(d -> d.getYear() == year);
    }
}
