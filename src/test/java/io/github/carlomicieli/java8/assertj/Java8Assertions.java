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
package io.github.carlomicieli.java8.assertj;

import org.assertj.core.api.Assertions;

import java.util.concurrent.CompletableFuture;

/**
 * @author Carlo Micieli
 */
public class Java8Assertions extends Assertions {

    // add the custom assertions related to MyProject
    public static <T> CompletableFutureAssert<T> assertThat(CompletableFuture<T> actual) {
        return new CompletableFutureAssert<>(actual);
    }
}
