package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private int modCount;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        boolean rsl = true;
        if (head == null || head.next == null) {
            rsl = false;
        }
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> newNext = current.next;
            current.next = prev;
            prev = current;
            current = newNext;
        }
        head = prev;
        return rsl;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        head = node.next;
        node.next = null;
        modCount++;
        size--;
        return node.value;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
           private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
       private T value;
       private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}