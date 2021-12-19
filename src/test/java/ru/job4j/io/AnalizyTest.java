package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void chekServer() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println(
                     "500 10:57:01;\n"
                   + "400 10:58:01;\n"
                   + "500 11:01:02;\n"
                   + "200 11:02:02;\n"
                   + "300 11:03:34;\n"
                   + "400 11:12:10;\n"
                   + "500 11:15:34;\n"
                   + "200 11:20:55;");
        }
        Analizy analizy = new Analizy();
        StringBuilder rsl = new StringBuilder();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01; - 11:02:02;11:12:10; - 11:20:55;"));
    }
}
