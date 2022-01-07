package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";

    private static final String STOP = "стоп";

    private static final String CONTINUE = "продолжить";

    private final List<String> log = new ArrayList<>();

    private final String path;

    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> text = readPhrases();
        log.add("Добро пожаловать!!!");
        System.out.println(log.get(0));
        Scanner scanner = new Scanner(System.in);
        String sel = scanner.nextLine();
        log.add(sel);
        boolean chatWork = true;
        while (!OUT.equals(sel)) {
            if (STOP.equals(sel)) {
                chatWork = false;
            }
            if (CONTINUE.equals(sel)) {
                chatWork = true;
            }
            if (chatWork) {
                int index = (int) (Math.random() * text.size());
                System.out.println(text.get(index));
                log.add(text.get(index));
            }
            sel = scanner.nextLine();
            log.add(sel);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> text = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers,
                Charset.forName("WINDOWS-1251")))) {
        in.lines().forEach(text::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(path, Charset.forName("WINDOWS-1251"))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\src\\data\\botLog.txt",
                "C:\\projects\\job4j_design\\src\\data\\botAnswer.txt");
        cc.run();
    }
}
