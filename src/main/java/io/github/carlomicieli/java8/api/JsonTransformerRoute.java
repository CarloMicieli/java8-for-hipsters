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
package io.github.carlomicieli.java8.api;

import com.google.gson.*;
import io.github.carlomicieli.java8.football.PlayingSurface;
import io.github.carlomicieli.java8.football.RoofType;
import spark.ResponseTransformerRoute;

/**
 * @author Carlo Micieli
 */
public abstract class JsonTransformerRoute extends ResponseTransformerRoute {

    private static final JsonSerializer<PlayingSurface> playingSurfaceSerial =
            (src, typeOf, context) -> new JsonPrimitive(src.toString());
    private static final JsonSerializer<RoofType> roofTypeSerial =
            (src, typeOf, context) -> new JsonPrimitive(src.toString());

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(PlayingSurface.class, playingSurfaceSerial)
            .registerTypeAdapter(RoofType.class, roofTypeSerial)
            .create();

    protected JsonTransformerRoute(String path) {
        super(path, "application/json");
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}

