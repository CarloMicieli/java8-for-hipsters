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
package io.github.carlomicieli.java8.states;

import org.junit.Test;

import java.time.LocalDate;

import static io.github.carlomicieli.java8.states.StateTestsHelper.california;
import static io.github.carlomicieli.java8.states.StateTestsHelper.rhodeIsland;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class StateTests {

    @Test
    public void shouldCreateNewStates() {
        State x = california();
        assertThat(x.getAbbreviation(), is(equalTo("CA")));
        assertThat(x.getArea(), is(equalTo(423_968)));
        assertThat(x.getCapital(), is(equalTo("Sacramento")));
        assertThat(x.getName(), is(equalTo("California")));
        assertThat(x.getPopulation(), is(equalTo(38_332_521L)));
        assertThat(x.getStatehood(), is(LocalDate.of(1850, 9, 9)));
    }

    @Test
    public void shouldCheckWhetherTwoStatesAreEquals() {
        State x = california();
        State y = california();
        assertThat(x.equals(x), is(true));
        assertThat(x.equals(y), is(true));
    }

    @Test
    public void shouldCheckWhetherTwoStatesAreDifferent() {
        State x = california();
        State y = rhodeIsland();
        assertThat(x.equals(y), is(false));
    }

    @Test
    public void shouldCalculateHashCodesForStates() {
        State x = california();
        State y = california();
        assertThat(x.hashCode(), is(equalTo(y.hashCode())));
    }

    @Test
    public void shouldProduceStringRepresentationFormStates() {
        final String EXPECTED = "California, CA, 1850-09-09, 38332521, Sacramento, 423968";
        String s = california().toString();
        assertThat(s, is(equalTo(EXPECTED)));
    }
}
