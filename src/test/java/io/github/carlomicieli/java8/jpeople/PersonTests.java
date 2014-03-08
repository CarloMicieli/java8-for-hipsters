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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PersonTests {

    @Test
    public void shouldProduceDisplayablePersonName() {
        Person p = new Person() {
            @Override
            public long getId() {
                return 0;
            }
        };
        assertThat(p.getDisplayableName(), is(equalTo("0")));
    }

    @Test
    public void shouldCreateDisplayableNames() {
        assertThat(Person.completeName("First", null).isPresent(), is(false));
        assertThat(Person.completeName(null, "Last").isPresent(), is(false));
        assertThat(Person.completeName("First", "Last").get(), is(equalTo("First Last")));
    }
}
