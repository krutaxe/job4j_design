package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(false, 5,
                "red", new Engine("diesel", 151),
                new String[]{"Ivanov", "Petrov"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String carJson =
                "{"
                        + "\"newCar\":false,"
                        + "\"door\":5,"
                        + "\"color\":\"red\","
                        + "\"engine\":"
                        + "{"
                        + "\"type\":\"diesel\","
                        + "\"power\":\"151\""
                        + "},"
                        + "\"users\":"
                        + "[\"Ivanov\", \"Petrov\"]"
                        + "}";

        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
