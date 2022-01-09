package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private boolean newCar;
    private int door;
    private String color;
    private Engine engine;
    private String[] users;

    public Car(boolean newCar, int door, String color, Engine engine,
               String[] users) {
        this.newCar = newCar;
        this.door = door;
        this.color = color;
        this.engine = engine;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Car{"
                + "newCar=" + newCar
                + ", door=" + door
                + ", color='" + color + '\''
                + ", engine=" + engine
                + ", users=" + Arrays.toString(users)
                + '}';
    }
}

