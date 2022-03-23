package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Java");
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(path, duplicatesVisitor);
        duplicatesVisitor.getDuplicates().forEach(
                e -> System.out.println(e.toAbsolutePath()));
    }
}