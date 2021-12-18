package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> out = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.stream().filter(line -> !line.contains("#") && line.length() > 0)
                .forEach(line -> {
                    String[] el = line.split("=");
                    if (el.length < 2) {
                        throw new IllegalArgumentException("pair not found error");
                    } else {
                        values.put(el[0], el[1]);
                    }
                });
    }

    public String value(String key) {
        if (values.get(key) == null) {
            throw  new IllegalArgumentException("pair not found error");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/pair_without_comment.properties");
        config.load();
    }

}