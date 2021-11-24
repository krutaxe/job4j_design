package ru.job4j.generics;

import java.util.Objects;

public class Predator extends Animal {
    private boolean fly;

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Predator predator = (Predator) o;
        return fly == predator.fly;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fly);
    }

    @Override
    public String toString() {
        return "Predator{"
                + "fly=" + fly
                + '}';
    }
}
