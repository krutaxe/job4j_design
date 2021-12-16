package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    out.write(Integer.toString((i + 1) * (j + 1)).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}