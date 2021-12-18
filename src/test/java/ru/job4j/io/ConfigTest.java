package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void pairValueNotFound() {
        String path = "./data/pair_value_not_found.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void pairKeyNotFound() {
        String path = "./data/pair_key_not_found.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test
    public void comment() {
        String path = "./data/comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }
}