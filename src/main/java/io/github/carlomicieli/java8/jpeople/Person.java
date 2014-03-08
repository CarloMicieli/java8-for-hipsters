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
package io.github.carlomicieli.java8.jpeople;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Carlo Micieli
 */
public interface Person {

    public long getId();
    public default String getDisplayableName() {
        return String.join(", ", Long.toString(getId()));
    }

    public static Optional<String> completeName(String firstName, String lastName) {
        Function<String, Optional<String>> concat =
                s1 -> Optional.ofNullable(lastName).map(s -> String.join(" ", s1, s));
        return Optional.ofNullable(firstName)
                .flatMap(concat::apply);
    }

    public static Stream<Employee> employees() {
        List<Employee> emp = Arrays.asList(
                new Employee(1, "John", "Doe", 20_000),
                new Employee(2, "Jane", "Doe", 32_000),
                new Employee(3, "Ryan", "Smith", 32_200)
        );
        return emp.stream();
    }
}
