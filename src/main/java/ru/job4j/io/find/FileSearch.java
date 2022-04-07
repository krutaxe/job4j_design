package ru.job4j.io.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FileSearch {
    public  static void validateArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory not found");
        }
    }

    public static List<Path> searcher(Path path, Predicate<Path> predicate) throws IOException {
        SearchFiles searchFiles = new SearchFiles(predicate);
        Files.walkFileTree(path, searchFiles);
        return searchFiles.getPaths();
    }

    public static void writer(String output, List<Path> pathList) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(output))) {
            pathList.forEach(printWriter::println);
        }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        String dir = argsName.get("d");
        String name = argsName.get("n");
        String output = argsName.get("o");

        Path dirPath = Paths.get(dir);
        List<Path> pathList = searcher(dirPath, path -> path.toFile().getName().endsWith(name));
        pathList.forEach(System.out::println);
        writer(output, pathList);
    }
}
