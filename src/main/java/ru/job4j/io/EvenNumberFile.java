package ru.job4j.io;
import java.io.*;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("even.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                int i = Integer.parseInt(line);
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
