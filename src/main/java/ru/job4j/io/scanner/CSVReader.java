package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static final String STDOUT = "stdout";

    public static void handle(ArgsName argsName) throws Exception {
        validateParam(argsName);
        String path = argsName.get("path");
        String target = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String outColumns = argsName.get("filter");
        String[] fields = outColumns.split(",");
        boolean isStdout = STDOUT.equals(target);

        try (Scanner scanner = new Scanner(new FileReader(path))) {
            List<String> csv = List.of(scanner.nextLine().split(delimiter));
            StringBuilder builder = new StringBuilder();
            builder.append(String.join(delimiter, fields)).append(System.lineSeparator());

            List<Integer> index = new ArrayList<>();
            for (String field: fields) {
                int fi = csv.indexOf(field);
                if (fi != -1) {
                    index.add(fi);
                }
            }

            while (scanner.hasNext()) {
                String[] csvRow = scanner.nextLine().split(delimiter);
                List<String> values = new ArrayList<>();
                for (int i : index) {
                    values.add(csvRow[i]);
                }
                builder.append(String.join(delimiter, values)).append(System.lineSeparator());
            }

            if (isStdout) {
                System.out.println(builder);
            } else {
                try (PrintWriter printWriter = new PrintWriter(new FileWriter(target))) {
                    printWriter.write(String.valueOf(builder));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateParam(ArgsName args) {
        if (args.size() != 4) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        if (!Paths.get(args.get("path")).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'path' is not file");
        }
        String target = args.get("out");
        if (!STDOUT.equals(target) && !Paths.get(target).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'out' is not file");
        }
        if (args.get("delimiter") == null) {
            throw new IllegalArgumentException("Delimiter is null");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsNames = ArgsName.of(args);
        handle(argsNames);
    }
}
