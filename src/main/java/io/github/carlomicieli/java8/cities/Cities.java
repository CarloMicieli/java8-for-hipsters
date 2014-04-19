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
package io.github.carlomicieli.java8.cities;

import com.google.gson.reflect.TypeToken;
import io.github.carlomicieli.java8.helpers.Loader;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Carlo Micieli
 */
public class Cities {
    private static final String DATA_JAPAN_JSON = "/data/japan.json";
    private static final List<City> data = citiesList();

    public static Stream<City> stream() {
        return data.stream();
    }

    private static List<City> citiesList() {
        Loader l = new Loader(DATA_JAPAN_JSON);
        return l.load(new TypeToken<List<City>>() {}.getType());
    }

}
