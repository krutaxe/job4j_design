package ru.job4j.io;

import java.io.*;
public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String temp = null;
            StringBuilder down = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                if ((line.contains("400") || line.contains("500")) && temp == null) {
                temp = line.split(" ")[1];
                down.append(temp);
                } else if (temp != null && (line.contains("200") || line.contains("300"))) {
                    temp = line.split(" ")[1];
                    down.append(" - ").append(temp);
                    down.append(System.lineSeparator());
                    temp = null;
                }
            }
            out.println(down);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server_log.txt", "down_server.txt");
    }
}