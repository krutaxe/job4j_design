package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && birthday == user.birthday
                && Objects.equals(name, user.name);
    }

    public static void main(String[] args) {
        User user1 = new User("Elena", 1, 1990);
        User user2 = new User("Elena", 1, 1990);

        Map<User, Object> map1 = new HashMap<>();
        map1.put(user1, new Object());
        map1.put(user2, new Object());

        for (Map.Entry<User, Object> entry: map1.entrySet()) {
            System.out.println(entry);
        }

        User user3 = new User("Dima", 3, 1999);
        User user4 = new User("Dima", 3, 1999);

        Map<User, Object> map2 = new HashMap<>();
        map2.put(user3, new Object());
        map2.put(user4, new Object());

        for (Map.Entry<User, Object> entry: map2.entrySet()) {
            System.out.println(entry);
        }
    }
}