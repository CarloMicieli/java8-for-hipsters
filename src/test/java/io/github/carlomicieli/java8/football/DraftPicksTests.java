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
import java.util.Map;
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

    @Test
    public void draftRounds_shouldReturnTheDraftRoundsWithGivenYear() {
        Map<Integer, List<DraftPick>> rounds = DraftPicks.draftRounds(2012);
        assertThat(rounds.size(), is(equalTo(7)));
        assertThatRoundHasAtLeast30Picks(rounds, 1);
        assertThatRoundHasAtLeast30Picks(rounds, 2);
        assertThatRoundHasAtLeast30Picks(rounds, 3);
        assertThatRoundHasAtLeast30Picks(rounds, 4);
        assertThatRoundHasAtLeast30Picks(rounds, 5);
        assertThatRoundHasAtLeast30Picks(rounds, 6);
        assertThatRoundHasAtLeast30Picks(rounds, 7);
    }

    @Test
    public void draftRoundPicks_shouldReturnThePicksListWithTheGivenYearAndRound() {
        List<DraftPick> picks = DraftPicks.draftRoundPicks(2012, 3);
        assertThatContains3rdRoundPicksFrom2012Draft(picks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void draftRoundPicks_shouldThrowIllegalArgumentException_WhenRoundNumberIsInvalid() {
        DraftPicks.draftRoundPicks(2012, 0);
    }

    @Test
    public void picksByPosition_shouldReturnTheNumberOfPicksForEachPosition() {
        Map<String, Long> counters = DraftPicks.picksByPosition(2012);
        assertThat(counters, hasEntry("QB", 11L));
    }

    @Test
    public void picksByCollege_shouldReturnThePicksWithTheGivenCollege() {
        List<DraftPick> picks = DraftPicks.picksByCollege("LSU");
        assertThat(picks, hasSize(51));
    }

    @Test
    public void picksByCollegeAndRound_shouldReturnThePicksWithTheGivenCollegeAndRound() {
        List<DraftPick> picks = DraftPicks.picksByCollegeAndRound("LSU", 1);
        assertThatContains1stRoundPicksFromLSU(picks);
    }

    private void assertThatRoundHasAtLeast30Picks(Map<Integer, List<DraftPick>> rounds, int r) {
        assertThat(rounds, hasKey(r));
        assertThat("Round: " + r, rounds.get(r).size(), is(greaterThan(29)));
    }

    private void assertThatContains3rdRoundPicksFrom2012Draft(List<DraftPick> picks) {
        assertThat(picks, hasSize(32));
        List<String> pickNames = getDraftPickNamesAndOverall(picks);

        assertThat(pickNames,
                contains("1. Dwayne Allen",
                        "2. Trumaine Johnson",
                        "3. Josh Robinson",
                        "4. Ronnie Hillman",
                        "5. DeVier Posey",
                        "6. T.J. Graham",
                        "7. Bryan Anger",
                        "8. Josh LeRibeus",
                        "9. Olivier Vernon",
                        "10. Brandon Taylor",
                        "11. Donald Stephenson",
                        "12. Russell Wilson",
                        "13. Brandon Brooks",
                        "14. Demario Davis",
                        "15. Michael Egnew",
                        "16. Brandon Hardin",
                        "17. Jamell Fleming",
                        "18. Tyrone Crawford",
                        "19. Mike Martin",
                        "20. Mohamed Sanu",
                        "21. Bernard Pierce",
                        "22. Bill Bentley",
                        "23. Sean Spence",
                        "24. John Hughes",
                        "25. Nick Foles",
                        "26. Akiem Hicks",
                        "27. Jake Bequette",
                        "28. Lamar Holmes",
                        "29. T.Y. Hilton",
                        "30. Brandon Thompson",
                        "31. Jayron Hosley",
                        "32. Tony Bergstrom"));
    }

    private void assertThatContains1stRoundPicksFromLSU(List<DraftPick> picks) {
        assertThat(picks, hasSize(12));

        List<String> pickNames = getDraftPickNamesAndOverall(picks);
        assertThat(pickNames, contains("30. Joseph Addai",
                "1. JaMarcus Russell",
                "6. LaRon Landry",
                "23. Dwayne Bowe",
                "30. Craig Davis",
                "5. Glenn Dorsey",
                "3. Tyson Jackson",
                "5. Patrick Peterson",
                "6. Morris Claiborne",
                "14. Michael Brockers",
                "6. Barkevious Mingo",
                "18. Eric Reid"));
    }

    private List<String> getDraftPickNamesAndOverall(List<DraftPick> picks) {
        return picks.stream()
                    .map(p -> p.getPick() + ". " + p.getName())
                    .collect(Collectors.toList());
    }
}
