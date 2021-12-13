package ru.job4j.map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        if (table[index] == null) {
            rsl = true;
        }
        table[index] = entry;
        modCount++;
        count++;
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity = capacity * 2;
        int index;
        int hash;
        MapEntry<K, V>[] temp = new MapEntry[capacity];
        for (MapEntry<K, V> el: table) {
             hash = (el.key == null) ? 0 : hash(el.key.hashCode());
             index = indexFor(hash);
             temp[index] = el;
        }
        table = temp;
    }

    @Override
    public V get(K key) {
        V value = null;
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null) {
           K tableKey = table[index].key;
           value = tableKey.equals(key) ? table[index].value : null;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (get(key) != null) {
            int hash = (key == null) ? 0 : hash(key.hashCode());
            int index = indexFor(hash);
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int point = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < count;
            }

            @Override
            public K next() {
                K rsl = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (; index < table.length; index++) {
                    if (table[index] != null) {
                        rsl = table[index].key;
                        point++;
                        index++;
                        break;
                    }
                }
                return rsl;
            }
        };
    }

    private static class MapEntry<K, V> {
       private final K key;
        private final V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}