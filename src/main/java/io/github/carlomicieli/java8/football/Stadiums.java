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
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Carlo Micieli
 */
public final class Stadiums {

    private static final String STADIUMS_DATA_JSON = "/data/stadiums.json";
    private static final List<Stadium> data = stadiumList();

    public static Stream<Stadium> stream() {
        return data.stream();
    }

    private static List<Stadium> stadiumList() {
        Loader l = new Loader(STADIUMS_DATA_JSON);
        return l.load(new TypeToken<List<Stadium>>() {}.getType());
    }

    public static Optional<Stadium> findById(int id) {
        return stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public static Optional<Stadium> findByName(String stadiumName) {
        return stream()
                .filter(s -> s.getName().equals(stadiumName))
                .findFirst();
    }
}
