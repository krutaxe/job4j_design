package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPojo {
    public static void main(String[] args) {
        JSONObject jsonEngine = new JSONObject("{\"type\":\"diesel\", \"power\":\"151\"}");

        List<String> list = new ArrayList<>();
        list.add("Ivanov");
        list.add("Petrov");
        JSONArray jsonUserses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car(false, 5, "red", new
                Engine("diesel", 151), new String[]{"Ivanov", "Petrov"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newCar", car.isNewCar());
        jsonObject.put("door", car.getDoor());
        jsonObject.put("color", car.getColor());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("userses", jsonUserses);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(car).toString());
    }
}
