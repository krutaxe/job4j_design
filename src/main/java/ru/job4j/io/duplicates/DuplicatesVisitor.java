package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, List<Path>> map = new HashMap<>();

    public HashMap<FileProperty, List<Path>> getMap() {
        return map;
    }

    public void getDuplicate() {
        List<List<Path>> resultList = new ArrayList<>();
        for (Map.Entry<FileProperty, List<Path>> entry: map.entrySet()) {
            if (entry.getValue().size() > 1) {
                resultList.add(entry.getValue());
            }
        }
        System.out.println(resultList);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fileProperty = new FileProperty(
                file.toFile().length(), file.toFile().getName());
        if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file);
        } else {
            List<Path> pathList = new ArrayList<>();
            pathList.add(file);
            map.put(fileProperty, pathList);
        }
        return FileVisitResult.CONTINUE;
    }
}