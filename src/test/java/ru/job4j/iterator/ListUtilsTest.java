package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 5, 6));
        ListUtils.removeIf(input, el -> el % 2 == 1 && el > 2);
        assertThat(input, is(Arrays.asList(0, 1, 2, 6)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, el -> el / 2 == 2 || el < 2, 100);
        assertThat(input, is(Arrays.asList(100, 2, 3, 100)));
    }

    @Test
    public void whenReplaceAll() {
        List<String> list = new ArrayList<>(List.of("ok", "Hi", "Hello", "Java", "World"));
        List<String> elements = new ArrayList<>(List.of("ok", "World", "Hi"));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList("Hello", "Java")));
    }

}