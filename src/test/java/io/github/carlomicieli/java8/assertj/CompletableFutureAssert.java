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

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Carlo Micieli
 */
public class CompletableFutureAssert<T> extends AbstractAssert<CompletableFutureAssert<T>, CompletableFuture<T>> {
    protected CompletableFutureAssert(CompletableFuture<T> actual) {
        super(actual, CompletableFutureAssert.class);
    }

    public static <T> CompletableFutureAssert<T> assertThat(CompletableFuture<T> actual) {
        return new CompletableFutureAssert<>(actual);
    }

    public CompletableFutureAssert<T> isDone() {
        isNotNull();

        if (!actual.isDone()) {
            failWithMessage("Expected to be done, but it wasn't");
        }

        return this;
    }

    public CompletableFutureAssert<T> isCompleted() {
        isNotNull();

        if (actual.isCompletedExceptionally()) {
            failWithMessage("Expected to be completed successfully, but it completed exceptionally");
        }

        if (!actual.isDone()) {
            failWithMessage("Expected to be completed successfully, but it wasn't done yet");
        }

        return this;
    }

    public CompletableFutureAssert<T> isCompletedExceptionally() {
        isNotNull();

        waitCompletion();
        if (!actual.isCompletedExceptionally()) {
            failWithMessage("Expected to be completed exceptionally, but it wasn't");
        }

        return this;
    }

    public CompletableFutureAssert<T> completedWith(T value) {
        isNotNull();

        T actualValue = waitResult().orElse(null);
        if (!actualValue.equals(value)) {
            failWithMessage("Expected value to be %s but was %s", Objects.toString(value), Objects.toString(actualValue));
        }

        return this;
    }

    public CompletableFutureAssert<T> completedWith(T value, long timeout, TimeUnit unit) {
        isNotNull();

        try {
            T actualValue = actual.get(timeout, unit);
            if (!actualValue.equals(value)) {
                failWithMessage("Expected value to be %s but was %s", Objects.toString(value), Objects.toString(actualValue));
            }
        } catch(InterruptedException | ExecutionException e) {
            failWithMessage(e.getMessage());
        } catch (TimeoutException e) {
            failWithMessage("Timeout expired before it was completed.");
        }

        return this;
    }

    private Optional<T> waitResult() {
        try {
            return Optional.ofNullable(actual.get());
        } catch(InterruptedException | ExecutionException e) {
            failWithMessage(e.getMessage());
        }

        return Optional.empty();
    }

    private void waitCompletion() {
        try {
            actual.get();
        } catch(InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}


