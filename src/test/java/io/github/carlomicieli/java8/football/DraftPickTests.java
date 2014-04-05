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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class DraftPickTests {
    private final static int DRAFT_YEAR_2006 = 2006;
    private final static int DRAFT_YEAR_2007 = 2007;
    private final static int ROUND_1ST = 1;
    private final static int PICK_16 = 16;
    private final static int PICK_6 = 6;
    private final static int OVERALL_16TH = 16;
    private final static int OVERALL_6TH = 6;
    private final static String SF_49ERS = "SF";
    private final static String PATRICK_WILLIS = "Patrick Willis";
    private final static String VERNON_DAVIS = "Vernon Davis";
    private final static String LINEBACKER = "LB";
    private final static String TIGHT_END = "TE";
    private final static String OLE_MISS = "Ole Miss";
    private final static String MARYLAND = "Maryland";
    private final static int LAST_YEAR = 2013;

    @Test
    public void shouldCreateNewDraftPicks() {
        DraftPick pick = createDraftPick_For_PatrickWillis();
        assertThatDraftPick_Is_PatrickWillis(pick);
    }

    @Test
    public void shouldCheckWhetherTwoDraftPicksAreDifferent() {
        DraftPick x = createDraftPick_For_PatrickWillis();
        DraftPick y = createDraftPick_For_VernonDavis();
        assertThat(x.equals(y), is(equalTo(false)));
    }

    @Test
    public void shouldCheckWhetherTwoDraftPicksAreEquals() {
        DraftPick x = createDraftPick_For_PatrickWillis();
        DraftPick y = createDraftPick_For_PatrickWillis();
        assertThat(x.equals(x), is(equalTo(true)));
        assertThat(x.equals(y), is(equalTo(true)));
    }

    @Test
    public void shouldCalculateHashCodeForDraftPicks() {
        DraftPick x = createDraftPick_For_PatrickWillis();
        DraftPick y = createDraftPick_For_PatrickWillis();
        assertThat(x.hashCode(), is(equalTo(y.hashCode())));
    }

    @Test
    public void shouldReturnStringRepresentationForDraftPicks() {
        DraftPick x = createDraftPick_For_PatrickWillis();
        String expected = "1st round, 16th pick (16th overall) Patrick Willis (Ole Miss)";
        assertThat(x.toString(), is(equalTo(expected)));
    }

    private DraftPick createDraftPick_For_PatrickWillis() {
        return new DraftPick(DRAFT_YEAR_2007,
                ROUND_1ST,
                PICK_16,
                OVERALL_16TH,
                SF_49ERS,
                PATRICK_WILLIS,
                LINEBACKER,
                OLE_MISS,
                LAST_YEAR);
    }

    private DraftPick createDraftPick_For_VernonDavis() {
        return new DraftPick(DRAFT_YEAR_2006,
                ROUND_1ST,
                PICK_6,
                OVERALL_6TH,
                SF_49ERS,
                VERNON_DAVIS,
                TIGHT_END,
                MARYLAND,
                LAST_YEAR);
    }

    private void assertThatDraftPick_Is_PatrickWillis(DraftPick pick) {
        assertThat(pick.getYear(), is(equalTo(DRAFT_YEAR_2007)));
        assertThat(pick.getRound(), is(equalTo(ROUND_1ST)));
        assertThat(pick.getPick(), is(equalTo(PICK_16)));
        assertThat(pick.getOverall(), is(equalTo(OVERALL_16TH)));
        assertThat(pick.getTeam(), is(equalTo(SF_49ERS)));
        assertThat(pick.getName(), is(equalTo(PATRICK_WILLIS)));
        assertThat(pick.getPosition(), is(equalTo(LINEBACKER)));
        assertThat(pick.getCollege(), is(equalTo(OLE_MISS)));
        assertThat(pick.getLastYear(), is(equalTo(LAST_YEAR)));
    }
}
