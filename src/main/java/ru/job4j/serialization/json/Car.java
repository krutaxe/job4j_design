package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    boolean newCar;
    int door;
    String color;
    Engine engine;
    String[] users;

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

