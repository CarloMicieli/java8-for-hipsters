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
package io.github.carlomicieli.java8.helpers;

import com.google.gson.*;
import io.github.carlomicieli.java8.football.PlayingSurface;
import io.github.carlomicieli.java8.football.RoofType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Carlo Micieli
 */
public class Loader {

    private final String filename;

    public Loader(String filename) {
        this.filename = filename;
    }

    public <T> T load(Type type) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, localDateDeserializer())
                .registerTypeAdapter(PlayingSurface.class, surfaceDeserializer())
                .registerTypeAdapter(RoofType.class, roofDeserializer())
                .create();
        InputStream is = getClass().getResourceAsStream(filename);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            return gson.fromJson(in, type);
        }
        catch (Exception e) {
            throw new LoadingException(e.getMessage());
        }
    }

    private JsonDeserializer<LocalDate> localDateDeserializer() {
        final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return (json, typeOf, context) -> {
            if (json == null)
                return null;
            else
                return LocalDate.parse(json.getAsString(), fmt);
        };
    }

    private JsonDeserializer<PlayingSurface> surfaceDeserializer() {
        return (json, typeOf, context) -> {
            if (json == null)
                return null;
            else
                return PlayingSurface.parse(json.getAsString()).orElse(null);
        };
    }

    private  JsonDeserializer<RoofType> roofDeserializer() {
        return (json, typeOf, context) -> {
            if (json == null)
                return null;
            else
                return RoofType.parse(json.getAsString()).orElse(null);
        };
    }

    public static class LoadingException extends RuntimeException {
        public LoadingException(String msg) {
            super(msg);
        }
    }
}
