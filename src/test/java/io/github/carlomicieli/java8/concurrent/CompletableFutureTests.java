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
package io.github.carlomicieli.java8.concurrent;

import static io.github.carlomicieli.java8.assertj.Java8Assertions.assertThat;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author Carlo Micieli
 */
public class CompletableFutureTests {

    @Test
    public void shouldManuallyCreateNewCompletableFuturesAlreadyCompleted() {
        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(42);
        CompletableFuture<Integer> f2 = new CompletableFuture<>();
        f2.complete(42);

        assertThat(f1).isDone().completedWith(42);
        assertThat(f2).isDone().completedWith(42);
    }

    @Test
    public void shouldCreateNewCompletableFutureFromAsyncComputations() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(42));
        assertThat(f1).completedWith(42, 2, TimeUnit.SECONDS);
    }

    @Test
    public void shouldRecoverExceptionallySituations() {
        CompletableFuture<Integer> f1 = CompletableFuture
                .supplyAsync(CompletableFutureTests::taskWithException);
        CompletableFuture<Integer> f2 = f1.exceptionally(ex -> -1);

        assertThat(f2).completedWith(-1);
    }

    @Test
    public void shouldHandleEitherComputationResultsOrErrors() {
        BiFunction<Integer, Throwable, Integer> handlerFunction = (res, err) -> {
            if (res != null)
                return res;
            else {
                return -1;
            }
        };

        CompletableFuture<Integer> f1 = CompletableFuture
                .supplyAsync(CompletableFutureTests::taskWithException)
                .handle(handlerFunction);
        CompletableFuture<Integer> f2 = CompletableFuture
                .supplyAsync(() -> longRunningTask(42))
                .handle(handlerFunction);

        assertThat(f1).completedWith(-1);
        assertThat(f2).completedWith(42);
    }

    @Test
    public void shouldCompleteFutureExceptionallyIfExceptionsAreThrown() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(CompletableFutureTests::taskWithException);
        assertThat(f1).isCompletedExceptionally();
    }

    @Test
    public void shouldCreateNewCompletableFutureFromAsyncComputationsProvidingExecutors() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 42, Executors.newSingleThreadExecutor());
        assertThat(f1).completedWith(42);
    }

    @Test
    public void shouldApplyAFunctionWhenTheComputationEventuallyComplete() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(42))
                .thenApply(res -> res * 2);
        assertThat(f1).completedWith(84);
    }

    @Test
    public void shouldApplyAsyncComputationAfterTheFirstComputationEventuallyComplete() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(42))
                .thenApplyAsync(res -> longRunningTask(res * 2));
        assertThat(f1).completedWith(84);
    }

    @Test
    public void shouldCompleteExceptionallyWithoutApplyingTheFunctionIfTaskThrowsAnException() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(CompletableFutureTests::taskWithException)
                .thenApply(Object::toString);

        assertThat(f1).isCompletedExceptionally();
    }

    @Test
    public void shouldTerminateComputationIfResultIsProduced() throws Exception {
        CompletableFuture<?> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(42))
                .thenAccept(res -> assertThat(res).isEqualTo(42));

        f1.get();  // have to wait for f1 completion, as thenAccept() does not block
    }

    @Test
    public void shouldWaitTwoCompletableFuturesToComplete() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(42));
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> longRunningTask(84));

        CompletableFuture<?> f3 = f1.thenAcceptBothAsync(f2, (a, b) -> {
            assertThat(a).isEqualTo(42);
            assertThat(b).isEqualTo(84);
        });

        f3.get();  // have to wait for f1 completion, as thenAcceptBothAsync() does not block
    }

    @Test
    public void shouldWaitTheFirstCompletableFutureToComplete() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(42);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> longRunningTask(84));

        CompletableFuture<?> f3 = f1.acceptEither(f2, res -> assertThat(res).isEqualTo(42));
        f3.get();  // have to wait for f1 completion, as acceptEither() does not block
    }

    @Test
    public void shouldTransformTheFirstCompletableFutureToComplete() {
        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(42);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> longRunningTask(84));

        CompletableFuture<Integer> f3 = f1.applyToEither(f2, res -> res + 2);
        assertThat(f3).completedWith(44);
    }

    @Test
    public void shouldCombineTwoCompletableFutures() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(42))
                .thenCompose(CompletableFutureTests::numberToStringTask);
        assertThat(f1).completedWith("forty-two");
    }

    @Test
    public void shouldTransformTwoCompletableFutureValues() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 42);

        CompletableFuture<Integer> f3 = f1.thenCombine(f2, (a, b) -> a * b);
        assertThat(f3).completedWith(84);
    }

    @Test
    public void shouldAbortTransformationIfOneFutureCompleteExceptionally() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> { throw new RuntimeException(); });

        CompletableFuture<Integer> f3 = f1.thenCombine(f2, (a, b) -> a * b);
        assertThat(f3).isCompletedExceptionally();
    }

    @Test
    public void shouldWaitAllCompletableFuturesToComplete() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> longRunningTask(1));
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> longRunningTask(2));
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> longRunningTask(3));
        CompletableFuture<Integer> f4 = CompletableFuture.supplyAsync(() -> longRunningTask(4));

        CompletableFuture<?> fAll = CompletableFuture.allOf(f1, f2, f3, f4);

        fAll.get();
        assertThat(f1).isCompleted();
        assertThat(f2).isCompleted();
        assertThat(f3).isCompleted();
        assertThat(f4).isCompleted();
    }

    final static Random rnd = new Random();
    private static <T> T slowdownTask(long timeout, TimeUnit unit, Supplier<T> op) {
        try {
            long waitingTime = rnd.nextInt((int)unit.toMillis(timeout));
            TimeUnit.MILLISECONDS.sleep(waitingTime);
            return op.get();
        } catch (InterruptedException e) {
            return null;
        }
    }

    private static CompletableFuture<String> numberToStringTask(int n) {
        return CompletableFuture.supplyAsync(() -> slowdownTask(1, TimeUnit.SECONDS, () -> "forty-two"));
    }

    private static <T> T longRunningTask(T returnValue) {
        return slowdownTask(1, TimeUnit.SECONDS, () -> returnValue);
    }

    private static Integer taskWithException() {
        throw new RuntimeException("Error");
    }
}
