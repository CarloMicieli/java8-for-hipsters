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
package io.github.carlomicieli.java8.time;

import org.junit.Test;

import java.time.temporal.ChronoUnit;

import static io.github.carlomicieli.java8.time.DateTimeHelpers.instantNow;
import static io.github.carlomicieli.java8.time.DateTimeHelpers.laterInstant;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author Carlo Micieli
 */
public class TemporalUnitTests {

    @Test
    public void shouldCalculateDurationUsingChronoUnits() {
        long dur = ChronoUnit.SECONDS.between(instantNow(), laterInstant());
        assertThat(dur, is(equalTo(256L)));
    }
}
