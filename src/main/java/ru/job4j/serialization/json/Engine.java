package ru.job4j.serialization.json;

public class Engine {
    String type;
    int power;

    public Engine(String type, int power) {
        this.type = type;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", power=" + power
                + '}';
    }
}
