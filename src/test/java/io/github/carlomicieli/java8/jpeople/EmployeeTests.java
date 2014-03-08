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

import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class EmployeeTests {

    @Test
    public void shouldCreateNewEmployees() {
        LocalDate now = LocalDate.now();

        Employee emp = new Employee(1, "John", "Doe", 23_456, now);
        assertThat(emp.getId(), is(equalTo(1L)));
        assertThat(emp.getFirstName(), is(equalTo("John")));
        assertThat(emp.getLastName(), is(equalTo("Doe")));
        assertThat(emp.getSalary(), is(equalTo(23_456)));
        assertThat(emp.getHiringDate(), is(equalTo(now)));
    }

    @Test
    public void shouldProduceEmployeeStream() {
        Stream<Employee> stream = Person.employees();
        assertThat(stream.count(), is(equalTo(3L)));
    }
}
