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

import com.google.gson.*;
import io.github.carlomicieli.java8.football.PlayingSurface;
import io.github.carlomicieli.java8.football.RoofType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.ResponseTransformerRoute;

import java.util.function.BiFunction;

/**
 * @author Carlo Micieli
 */
public abstract class JsonTransformerRoute extends ResponseTransformerRoute {
    private final static Logger log = LoggerFactory.getLogger(JsonTransformerRoute.class);

    private static final JsonSerializer<PlayingSurface> playingSurfaceSerial =
            (src, typeOf, context) -> new JsonPrimitive(src.toString());
    private static final JsonSerializer<RoofType> roofTypeSerial =
            (src, typeOf, context) -> new JsonPrimitive(src.toString());
    public static final String APPLICATION_JSON = "application/json";

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(PlayingSurface.class, playingSurfaceSerial)
            .registerTypeAdapter(RoofType.class, roofTypeSerial)
            .create();

    protected JsonTransformerRoute(String path) {
        super(path, APPLICATION_JSON);
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }


    static JsonTransformerRoute newRoute(final String path, final BiFunction<Request, Response, Object> handler) {
        return new JsonTransformerRoute(path) {
            @Override
            public Object handle(Request request, Response response) {
                log.info("[{}]: request received!", path);
                response.type(APPLICATION_JSON);
                return handler.apply(request, response);
            }
        };
    }
}

