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

import io.github.carlomicieli.java8.football.Stadium;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

import static io.github.carlomicieli.java8.football.Stadium.newStadium;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class FunctionalInterfaceTests {

    @Test
    public void shouldCreateFunctionsWithOneArgument() {
        Function<String, String> upperFun = String::toUpperCase;
        assertThat(upperFun.apply("home"), is(equalTo("HOME")));
    }

    @Test
    public void shouldCreateBinaryOperations() {
        BinaryOperator<Integer> sumFun = (a, b) -> a + b;
        assertThat(sumFun.apply(1, 2), is(equalTo(3)));
    }

    @Test
    public void shouldCreateMaxFunctionFromBinaryOperators() {
        BinaryOperator<Stadium> maxOp = BinaryOperator.maxBy(Stadium::compareByCapacity);
        Stadium a = newStadium("a").capacity(1000).build();
        Stadium b = newStadium("b").capacity(500).build();

        assertThat(maxOp.apply(a, b), is(equalTo(a)));
    }

    @Test
    public void shouldCreateFunctionsWithTwoArguments() {
        BiFunction<Integer, Integer, Integer> sumFun = (a, b) -> a + b;
        assertThat(sumFun.apply(1, 2), is(equalTo(3)));
    }

    @Test
    public void shouldComposeFunctions() {
        Function<String, Integer> charsNum = s -> s.replace(" ", "").length();
        Function<Integer, Integer> doubled = n -> n * 2;

        Function<String, Integer> composed = charsNum.andThen(doubled);
        assertThat(composed.apply("hello world"), is(equalTo(20)));
    }

    @Test
    public void shouldCreatePredicates() {
        Predicate<String> longString = s -> s.length() > 10;
        assertThat(longString.test("hello"), is(equalTo(false)));
        assertThat(longString.test("hello world"), is(equalTo(true)));
    }

    @Test
    public void shouldComposePredicates() {
        Predicate<String> longString = s -> s.length() > 100;
        Predicate<String> startsWithHello = s -> s.startsWith("hello");

        Predicate<String> longAndStartsWithHello = longString.or(startsWithHello);
        assertThat(longAndStartsWithHello.test("hello world"), is(true));
    }

    @Test
    public void shouldUseLazyEvaluationForLogicalANDForPredicates() {
        Predicate<String> longString = s -> s.length() > 100;
        Predicate<String> neverEvaluated = s -> { throw new RuntimeException("Not called"); };

        boolean res = longString.and(neverEvaluated).test("hello");
        assertThat(res, is(false));
    }

    @Test
    public void shouldNegatePredicates() {
        Predicate<String> longString = s -> s.length() > 10;
        Predicate<String> shortString = longString.negate();
        assertThat(shortString.test("hello"), is(true));
    }

    @Test
    public void shouldSupplyValues() {
        Supplier<String> supplier = () -> "hello";
        assertThat(supplier.get(), is(equalTo("hello")));
        assertThat(supplier.get(), is(equalTo("hello")));
    }

    @Test
    public void shouldUseSuppliers() {
        List<String> l = TestClass.init(5, () -> "hello");
        assertThat(l.size(), is(equalTo(5)));
        assertThat(l, hasItem("hello"));
    }

    @Test
    public void shouldUseLambdaExpressionsForCustomInterfaces() {
        Function2<Integer, Integer, Long> sum = (a, b) -> 0L + a + b;
        assertThat(sum.apply(1, 2), is(equalTo(3L)));
    }

    @Test
    public void shouldReturnAFunctionFromMethods() {
        Function2<String, String, Integer> f = getFunction();
        assertThat(f.apply("hello", "world"), is(equalTo(10)));
    }

    static Function2<String, String, Integer> getFunction() {
        return (s1, s2) -> s1.length() + s2.length();
    }

    static class TestClass {
        static <T> List<T> init(int num, Supplier<T> sup) {
            List<T> l = new ArrayList<>(num);
            for(int i = 0; i < num; i++) {
                l.add(sup.get());
            }
            return l;
        }
    }

    @FunctionalInterface
    interface Function2<T, U, R> {
        R apply(T t, U u);

        // java: Unexpected @FunctionalInterface annotation
        // io.github.carlomicieli.java8.FunctionalInterfaceTests.Function2 is not a functional interface
        //        multiple non-overriding abstract methods found in interface
        //        io.github.carlomicieli.java8.FunctionalInterfaceTests.Function2
        //! void foo();
    }

}
