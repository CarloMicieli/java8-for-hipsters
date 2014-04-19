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
package io.github.carlomicieli.java8.tuples;

/**
 * @author Carlo Micieli
 */
public class Pair<A, B> {

    private final A fst;
    private final B snd;

    protected Pair(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public A first() {
        return fst;
    }

    public B second() {
        return snd;
    }

    @Override
    public String toString() {
        return "Pair(" + fst + ", " + snd + ")";
    }

    public static <A, B> boolean equals(Pair<A, B> a, Pair<A, B> b) {
        return a.first().equals(b.first()) &&
                a.second().equals(b.second());
    }
}
