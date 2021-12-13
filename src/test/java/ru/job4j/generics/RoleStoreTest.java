package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenUsername() {
        UserStore store = new UserStore();
        store.add(new User("4", "Dima"));
        User result = store.findById("4");
        assertThat(result.getUsername(), is("Dima"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Elena"));
        User result = store.findById("2");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsElena() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("2", "Elena"));
        store.add(new User("2", "Maxim"));
        User result = store.findById("2");
        assertThat(result.getUsername(), is("Elena"));
    }

    @Test
    public void whenReplaceThenUsernameIsMax() {
        UserStore store = new UserStore();
        store.add(new User("2", "Petr"));
        store.replace("2", new User("2", "Max"));
        User result = store.findById("2");
        assertThat(result.getUsername(), is("Max"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("5", "Max"));
        store.replace("7", new User("7", "Maxim"));
        User result = store.findById("5");
        assertThat(result.getUsername(), is("Max"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("2", "Max"));
        store.delete("2");
        User result = store.findById("2");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }
}