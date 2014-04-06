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
package io.github.carlomicieli.java8.helpers;

import java.util.Arrays;
import java.util.List;

/**
 * @author Carlo Micieli
 */
public class OrdinalNumber {

    /**
     * Returns the ordinal number String representation for the provided value.
     * @param n a number
     * @return the ordinal number
     */
    public static String of(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Ordinal number must be > 0");
        }

        return String.format("%d%s", n, suffixFor(n));
    }

    private static String suffixFor(int n) {
        if (isException(n)) return "th";
        switch (lastDigit(n)) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    private static int lastDigit(int n) {
        return lastTwoDigits(n) % 10;
    }

    private static int lastTwoDigits(int n) {
        return n % 100;
    }

    private static boolean isException(int n) {
        return RULE_EXCEPTIONS.contains(n);
    }

    private static final List<Integer> RULE_EXCEPTIONS = Arrays.asList(11, 12, 13);
}
