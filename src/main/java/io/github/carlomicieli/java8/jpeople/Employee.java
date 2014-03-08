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

import java.time.LocalDate;

/**
 * @author Carlo Micieli
 */
public class Employee implements Person {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final int salary;
    private final LocalDate hiringDate;

    public Employee(long id, String firstName, String lastName, int salary, LocalDate hiringDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hiringDate = hiringDate;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }
}
