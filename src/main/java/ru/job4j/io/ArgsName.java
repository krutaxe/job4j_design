package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private  void validateString(String arg) {
        if (!arg.startsWith("-") || !arg.contains("=") || arg.split("=").length != 2)  {
            throw new IllegalArgumentException("invalid argument parameters ");
        }
        if (arg.split("=")[0].substring(1).isEmpty() && arg.split("=")[1].isEmpty()) {
            throw new IllegalArgumentException("invalid argument parameters ");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters missed");
        }
        for (String arg: args) {
            validateString(arg);
            values.put(arg.split("=")[0].substring(1), arg.split("=")[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("encoding"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}