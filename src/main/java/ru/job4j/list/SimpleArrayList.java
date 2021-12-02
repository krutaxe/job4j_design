package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            container = Arrays.copyOf(container,
            container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T temp = container[index];
        container[index] = newValue;
        modCount++;
        return temp;

    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T temp = container[index];
        System.arraycopy(container,
                index + 1,
                container,
                index,
                container.length - index - 1);
        container[container.length - 1] = null;
        modCount++;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
          private final   int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return cursor < size && container[cursor] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
            return container[cursor++];
            }
        };
    }
}

