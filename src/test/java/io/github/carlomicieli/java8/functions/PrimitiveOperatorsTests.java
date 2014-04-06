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
package io.github.carlomicieli.java8.functions;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class PrimitiveOperatorsTests {
    @Test
    public void shouldFillArraysWithValuesGeneratedByIntUnaryOperator() {
        final int[] EXPECTED = new int[]{0, 1, 4, 9, 16};
        int[] square = new int[5];

        Arrays.setAll(square, n -> n * n);

        assertThat(square.length, is(equalTo(5)));
        assertThat(square, is(equalTo(EXPECTED)));
    }

    @Test
    public void intUnaryOperator_is_a_specialOperatorForPrimitiveIntegers() {
        IntUnaryOperator twoTimes = n -> 2 * n;
        int n = twoTimes.applyAsInt(12);
        assertThat(n, is(equalTo(24)));
    }

    @Test
    public void intUnaryOperator_shouldApplyASecondOperatorAfterTheFirstOne() {
        IntUnaryOperator twoTimes = n -> 2 * n;
        IntUnaryOperator plusFour = n -> 4 + n;

        IntUnaryOperator twoTimesAndThenPlusFour = twoTimes.andThen(plusFour);
        int result = twoTimesAndThenPlusFour.applyAsInt(10);
        assertThat(result, is(equalTo(24)));
    }

    @Test
    public void intUnaryOperator_shouldComposeTwoOperators() {
        IntUnaryOperator twoTimes = n -> 2 * n;
        IntUnaryOperator plusFour = n -> 4 + n;

        IntUnaryOperator plusFourAndThenTwoTimes = twoTimes.compose(plusFour);
        int result = plusFourAndThenTwoTimes.applyAsInt(10);
        assertThat(result, is(equalTo(28)));
    }

    @Test
    public void intUnaryOperator_identityOperatorAlwaysReturnItsInput() {
        IntUnaryOperator id = IntUnaryOperator.identity();
        assertThat(id.applyAsInt(42), is(equalTo(42)));
    }

    @Test
    public void intBinaryOperator_is_a_operation_uponTwoIntOperands_and_produceIntResults() {
        IntBinaryOperator intSumOp = Math::addExact;
        int result = intSumOp.applyAsInt(150, 168);
        assertThat(result, is(equalTo(318)));
    }

    @Test
    public void longBinaryOperator_is_a_operation_uponTwoLongOperands_and_produceLongResults() {
        LongBinaryOperator longSumOp = Math::addExact;
        long result = longSumOp.applyAsLong(150_345L, 168_234L);
        assertThat(result, is(equalTo(318_579L)));
    }

    @Test
    public void doubleSupplier_shouldSupplyDoubleValues() {
        DoubleSupplier sup = () -> 3.14d;
        assertThat(sup.getAsDouble(), is(closeTo(3.14d, 0.1)));
    }

    @Test
    public void booleanSupplier_shouldSupplyBooleanValues() {
        BooleanSupplier sup = () -> true;
        assertThat(sup.getAsBoolean(), is(true));
    }
}
