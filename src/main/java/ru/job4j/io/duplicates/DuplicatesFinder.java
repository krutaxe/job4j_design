package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Java");
        DuplicatesVisitor duplicates = new DuplicatesVisitor();
        Files.walkFileTree(path, duplicates);
        duplicates.getDuplicate();
    }
}