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

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PlayingSurfaceTests {

    @Test
    public void shouldProduceStringRepresentationsForSurface() {
        assertThat(PlayingSurface.A_TURF_TITAN.toString(), is(equalTo("A-Turf Titan")));
    }

    @Test
    public void shouldParseDescriptionStringToPlayingSurface() {
        Optional<PlayingSurface> ubu = PlayingSurface.parse("UBU-Intensity Series- S5-M Synthetic Turf");
        assertThat(ubu.get(), is(equalTo(PlayingSurface.UBU_INTENSITY_SERIES)));
    }

    @Test
    public void shouldReturnNoneForInvalidDescriptionParsingPlayingSurfaces() {
        Optional<PlayingSurface> notFound = PlayingSurface.parse("not found");
        assertThat(notFound.isPresent(), is(false));
    }

}
