package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int change = 0;
        int del = 0;

        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            String currentName = currentMap.get(user.getId());
            if (currentName != null && !Objects.equals(currentName, user.getName())) {
                change++;
                currentMap.remove(user.getId());
            } else if (currentName != null && Objects.equals(currentName, user.getName())) {
                currentMap.remove(user.getId());
            } else if (currentName == null) {
                del++;
            }
        }
    return new Info(currentMap.size(), change, del);
    }

}
