package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int del = 0;
        Info info = new Info(add, change, del);
        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            String currentName = currentMap.get(user.getId());
            if (currentName != null && !currentMap.get(user.getId()).equals(user.getName())) {
                change++;
                currentMap.remove(user.getId());
            } else if (currentName != null && currentMap.get(user.getId()).equals(user.getName())) {
                currentMap.remove(user.getId());
            } else if (currentName == null) {
                del++;
            }
        }
        add += currentMap.size();
        info.setAdded(add);
        info.setChanged(change);
        info.setDeleted(del);
    return info;
    }

}
