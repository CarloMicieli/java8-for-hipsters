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

import java.util.Arrays;
import java.util.List;

/**
 * @author Carlo Micieli
 */
public class PlayerPositions {

    private static final List<String> SPECIALISTS = Arrays.asList("K", "P", "LS");
    private static final List<String> DEFENSIVE_BACKS = Arrays.asList("S", "SS", "FS", "CB");

    public static boolean isSpecialist(Player player) {
        return SPECIALISTS.contains(player.getPosition());
    }

    public static boolean isDefensiveBack(Player player) {
        return DEFENSIVE_BACKS.contains(player.getPosition());
    }


}
