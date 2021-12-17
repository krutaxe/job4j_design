package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> line = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
          line =  in.lines().filter(s -> s.contains(" 404 ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(file)))) {
            for (String line: log) {
                out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}