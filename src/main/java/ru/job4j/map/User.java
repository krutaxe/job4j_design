package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private int birthday;

    public User(String name, int children, int birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
               + "name='" + name + '\''
               + ", children=" + children
               + ", birthday=" + birthday
               + '}';
    }

    public static void main(String[] args) {
        User user1 = new User("Elena", 1, 1990);
        User user2 = new User("Elena", 1, 1990);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> entry: map.entrySet()) {
            System.out.println(entry);
        }

    }
}
