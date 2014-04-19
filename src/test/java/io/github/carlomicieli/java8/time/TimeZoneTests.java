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
package io.github.carlomicieli.java8.time;

import org.junit.Test;

import java.time.ZoneId;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

/**
 * @author Carlo Micieli
 */
public class TimeZoneTests {

    @Test
    public void shouldGetTheListOfAvailableTimeZones() {
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        assertThat(allZones, hasSize(585));
    }

    @Test
    public void shouldGetTheMapWithShortZoneShortIds() {
        Map<String, String> m = ZoneId.SHORT_IDS;
        assertThat(m.size(), is(equalTo(28)));

        String ctt = m.get("CTT");
        assertThat(ctt, is(equalTo("Asia/Shanghai")));
    }

    @Test
    public void shouldGetTheTimeZonesForEurope() {
        ZoneId europeRome = ZoneId.of("Europe/Rome");
        assertThat(europeRome.getId(), is(equalTo("Europe/Rome")));
    }


}
