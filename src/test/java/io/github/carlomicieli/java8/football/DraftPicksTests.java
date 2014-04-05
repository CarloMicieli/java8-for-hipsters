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

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class DraftPicksTests {
    @Test
    public void shouldLoadDataForDraftPicks() {
        long count = DraftPicks.stream().count();
        assertThat(count, is(greaterThan(0L)));
    }

    @Test
    public void shouldReturnTheFirstRounderPicksForThe49ersAfter2010() {
        Predicate<DraftPick> firstRounders = p -> p.getRound() == 1;
        Predicate<DraftPick> picksFrom49ers = p -> p.getTeam().equals("SF");
        Predicate<DraftPick> picksAfterThe2010 = p -> p.getYear() >= 2010;

        List<String> names = DraftPicks.stream()
                .filter(firstRounders)
                .filter(picksFrom49ers)
                .filter(picksAfterThe2010)
                .map(DraftPick::getName)
                .sorted()
                .collect(Collectors.toList());
        assertThat(names, hasSize(5));
        assertThat(names, hasItems("A.J. Jenkins", "Aldon Smith", "Anthony Davis", "Eric Reid", "Mike Iupati"));
    }
}
