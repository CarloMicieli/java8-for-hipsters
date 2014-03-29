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

import com.google.gson.reflect.TypeToken;
import io.github.carlomicieli.java8.utils.Loader;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Carlo Micieli
 */
public class States {
    private static final String DATA_STATES_JSON = "/data/states.json";
    private static final List<State> data = statesList();

    public static Stream<State> stream() {
        return data.stream();
    }

    private static List<State> statesList() {
        Loader l = new Loader(DATA_STATES_JSON);
        return l.load(new TypeToken<List<State>>() {}.getType());
    }

}
