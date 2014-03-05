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

/**
 * @author Carlo Micieli
 */
public enum PlayingSurface {
    GRASS("Grass"),
    BERMUDA_GRASS("419 Tifway Bermuda Grass"),
    FIELD_TURF("FieldTurf"),
    UBU_INTENSITY_SERIES("UBU-Intensity Series- S5-M Synthetic Turf"),
    A_TURF_TITAN("A-Turf Titan"),
    DESSO_GRASSMASTER("Desso GrassMaster"),
    KENTUCKY_BLUEGRASS("Kentucky Bluegrass"),
    MATRIX_REALGRASS_TURF("Matrix RealGrass artificial turf"),
    SPORTEXE_MOMENTUM_TURF("Sportexe Momentum Turf"),
    ASTROTURF_GAMEDAY_GRASS("AstroTurf GameDay Grass 3D");

    private final String desc;

    private PlayingSurface(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
