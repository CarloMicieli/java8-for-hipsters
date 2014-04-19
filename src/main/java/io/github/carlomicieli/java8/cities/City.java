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
package io.github.carlomicieli.java8.cities;

import java.time.LocalDate;

/**
 * @author Carlo Micieli
 */
public final class City {
    private final String name;
    private final String prefecture;
    private final int population;
    private final double area;
    private final double density;
    private final LocalDate founded;

    public City(String name, String prefecture, int population, double area, double density, LocalDate founded) {
        this.name = name;
        this.prefecture = prefecture;
        this.population = population;
        this.area = area;
        this.density = density;
        this.founded = founded;
    }

    public String getName() {
        return name;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    public LocalDate foundedAt() {
        return founded;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof City)) return false;

        City that = (City) obj;
        return this.name.equals(that.name);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", prefecture='" + prefecture + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", density=" + density +
                ", founded=" + founded +
                '}';
    }
}