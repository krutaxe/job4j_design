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
import java.util.regex.Pattern;

public class FileSearch {
    private static void validateArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory not found");
        }
    }

    private static List<Path> searcher(Path path, Predicate<Path> predicate) throws IOException {
        SearchFiles searchFiles = new SearchFiles(predicate);
        Files.walkFileTree(path, searchFiles);
        return searchFiles.getPaths();
    }

    private static void writer(String output, List<Path> pathList) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(output))) {
            pathList.forEach(printWriter::println);
        }
    }

    private static Predicate<Path> predicate(String type, String name) {
        Predicate<Path> pr = p -> false;
        if (type.equals("mask")) {
            name = name.replace(".", "[.]");
            name = name.replace("*", ".*");
            name = name.replace("?", ".{1}");
            name = "^" + name + "$";
            Pattern pattern = Pattern.compile(name);
            pr = p -> pattern.matcher(p.getFileName().toString()).find();
        } else if (type.equals("name")) {
            String finalName = name;
            pr = p -> p.getFileName().toString().equals(finalName);
        }
        return pr;
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        String dir = argsName.get("d");
        String name = argsName.get("n");
        String type = argsName.get("t");
        String output = argsName.get("o");

        Predicate<Path> predicate = predicate(type, name);
        List<Path> pathList = searcher(Paths.get(dir), predicate);
        writer(output, pathList);
    }
}
