package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean newCar;

    @XmlAttribute
    private int door;

    @XmlAttribute
    private String color;

    private Engine engine;

    @XmlElementWrapper(name = "userses")
    @XmlElement(name = "users")
    private String[] users;

    public Car() {
    }

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


