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
public class Triple<A, B, C> extends Pair<A, B> {

    private final C trd;

    public Triple(A fst, B snd, C trd) {
        super(fst, snd);
        this.trd = trd;
    }

    public C third() {
        return trd;
    }

    @Override
    public String toString() {
        return "Triple(" + first() + ", " + second() + ", " + third() + ")";
    }
}
