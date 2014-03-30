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
package io.github.carlomicieli.java8.web;

import spark.Request;

import java.util.stream.Stream;

/**
 * @author Carlo Micieli
 */
public class StreamsHelper {

    public static <T> Stream<T> paginate(Stream<T> orig, Request req) {
        Paginate paging = Paginate.of(req);
        return orig.skip(paging.skip).limit(paging.limit);
    }

    static class Paginate {
        final int skip;
        final int limit;

        private Paginate(int page, int pageSize) {
            skip = (page - 1) * pageSize;
            limit = pageSize;
        }

        static Paginate of(Request req) {
            int page = param(req, "page", 1);
            int size = param(req, "size", 10);
            return new Paginate(page, size);
        }

        private static int param(Request req, String name, int defaultValue) {
            String val = req.queryParams(name);
            if (val == null || val.equals("")) return defaultValue;
            return Integer.parseInt(val);
        }
    }
}
