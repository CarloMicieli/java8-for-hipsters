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
package io.github.carlomicieli.java8;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class DefaultMethodsTests {

    @Test
    public void shouldUseDefaultInterfaceMethodsWhenThereIsNoOverride() {
        SecondClass tc = new SecondClass();
        assertThat(tc.foo(), is(equalTo("First.foo()")));
        assertThat(tc.bar(), is(equalTo("First.bar()")));
    }

    @Test
    public void shouldClassWinsOverDefaultInterfaceMethods() {
        FirstClass tc = new FirstClass();
        assertThat(tc.foo(), is(equalTo("First.foo()")));
        assertThat(tc.bar(), is(equalTo("FirstClass.bar()")));
    }

    @Test
    public void shouldDefineStaticMethods() {
        assertThat(Second.baz(), is(equalTo("Second.baz()")));
    }

    interface First {
        default String foo() {
            return "First.foo()";
        }

        default String bar() {
            return "First.bar()";
        }
    }

    interface Second {
        String foo();
        static String baz() {
            return "Second.baz()";
        }
    }

    class FirstClass implements First {
        @Override
        public String bar() {
            return "FirstClass.bar()";
        }
    }

    class SecondClass implements First {
    }

    @SuppressWarnings("unused")
    class ThirdClass implements First, Second {
        //It must override foo, as there is a default implementation.
        //! java: io.github.carlomicieli.java8.DefaultMethodsTests.ThirdClass is not abstract and does
        //      not override abstract method foo() in io.github.carlomicieli.java8.DefaultMethodsTests.Second
        @Override
        public String foo() {
            return First.super.foo();
        }
    }
}
