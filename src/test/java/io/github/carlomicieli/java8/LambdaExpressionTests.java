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
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class LambdaExpressionTests {

    @Test
    public void shouldDefinePredicateAsInterfaces() {
        MyPredicate<String> p = s -> s.length() > 5;
        assertThat(TestClass.applyPredicate(p, "123456"), is(true));
        assertThat(TestClass.applyPredicate(p, "1234"), is(false));
    }

    @Test
    public void shouldDefineFunctionsAsInterfaces() {
        MyFunction<String, String, Integer> f = (s1, s2) -> s1.length() + s2.length();
        assertThat(TestClass.applyFunction(f, "abc", "def"), is(equalTo(6)));
    }

    @Test
    public void lambdaExpressions_Are_Closures() {
        int a = 10;
        MyFunction<Integer, Integer, Integer> op = (n1, n2) -> a + (n1 * n2);

        //! a = 10;
        //local variables referenced from a lambda expression must be final or effectively final

        assertThat(op.apply(2, 3), is(equalTo(16)));
    }

    @Test
    public void functionalInterfaces_Can_Be_Replaced_With_MethodReferences() {
        assertThat(TestClass.applyPredicate(TestClass::method, "abc"), is(true));
    }


    @FunctionalInterface
    private static interface MyPredicate<T> {
        boolean apply(T t);
    }

    @FunctionalInterface
    private static interface MyFunction<A, B, R> {
        R apply(A a, B b);
    }

    private static class TestClass {
        static <T> boolean applyPredicate(MyPredicate<T> p, T t) {
            return p.apply(t);
        }

        static int applyFunction(MyFunction<String, String, Integer> fun, String a, String b) {
            return fun.apply(a, b);
        }

        static boolean method(String s) {
            return true;
        }
    }
}
