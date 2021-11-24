package ru.job4j.generics;

import java.util.Objects;

public class Animal {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return age == animal.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return "Animal{"
                + "age=" + age
                + '}';
    }
}
