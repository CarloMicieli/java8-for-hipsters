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

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class ConsumerTests {

    @Test
    public void consumer_shouldOperateViaSideEffects() {
        Consumer<StringBuilder> appendConsumer = sb -> sb.append("hello");
        StringBuilder sb = new StringBuilder();

        appendConsumer.accept(sb);

        assertThat(sb.toString(), is(equalTo("hello")));
    }

    @Test
    public void biConsumer_shouldOperateViaSideEffects() {
        BiConsumer<StringBuilder, String> appendConsumer = (sb, s) -> sb.append(s);
        StringBuilder sb = new StringBuilder();

        appendConsumer.accept(sb, "hello");

        assertThat(sb.toString(), is(equalTo("hello")));
    }

    @Test
    public void biConsumer_shouldComposeTwoConsumers() {
        BiConsumer<StringBuilder, String> appendConsumer = (sb, s) -> sb.append(s);

        StringBuilder sb = new StringBuilder();
        appendConsumer
                .andThen((sbl, s) -> sbl.append(s.toUpperCase()))
                .accept(sb, "hello");

        assertThat(sb.toString(), is(equalTo("helloHELLO")));
    }

    @Test(expected = RuntimeException.class)
    public void biConsumer_shouldThrowException_ComposingConsumers() {
        BiConsumer<Resource, Integer> cons = Resource::consume;
        cons.andThen(Resource::consume)
                .accept(new Resource(), 1);
    }

    private static class Resource {
        int num = 0;
        void consume(int i) {
            this.num += i;
            if (num == 2)
                throw new RuntimeException();
        }
    }
}
