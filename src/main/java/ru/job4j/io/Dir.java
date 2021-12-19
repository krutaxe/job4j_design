package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsoluteFile()));
        }
        System.out.printf("size disk C : %s%n",
                file.getTotalSpace() / (1024 * 1024) + " Mb");
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName() + " : " + subfile.length() / 1024 + " kb");

        }
    }
}