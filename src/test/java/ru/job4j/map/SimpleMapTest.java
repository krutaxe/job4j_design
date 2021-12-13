package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class SimpleMapTest {

    @Test
    public void put() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Assert.assertTrue(map.put(7, "Tom"));
        Assert.assertFalse(map.put(7, "Tom"));
    }

    @Test
    public void putTrue() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("Tom", 8));
    }

    @Test
    public void putFalse() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(8, 1);
        Assert.assertFalse(map.put(8, 1));
    }

    @Test
    public void putNewValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 11);
        map.put(1, 22);
        Assert.assertThat(map.get(1), is(22));

    }

    @Test
    public void getValue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Bob", "Bot");
        Assert.assertThat(map.get("Bob"), is("Bot"));
    }

    @Test
    public void getNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(0, 1);
        map.put(1, 2);
        Assert.assertThat(map.get(2), is(nullValue()));
    }

    @Test
    public void removeTrue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(4, 1);
        map.put(6, 2);
        Assert.assertTrue(map.remove(4));
    }

    @Test
    public void removeFalse() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(4, 1);
        map.put(6, 2);
        Assert.assertFalse(map.remove(3));
    }

    @Test
    public void iteration() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is("a"));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is("b"));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is("c"));
        Assert.assertFalse(iterator.hasNext());
    }
}