package ru.netology.javacore;

import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class Todos {

    public static final int MAX_TASKS = 7;

    public static final String DELIMITER = " ";

    private final Set<String> tasksList = new TreeSet<>();

    public void addTask(String task) {

        if (tasksList.size() < MAX_TASKS) {
            tasksList.add(task);
        }
    }

    public void removeTask(String task) {
        tasksList.remove(task);
    }

    public String getAllTasks() {
        StringJoiner sj = new StringJoiner(DELIMITER);

        tasksList.forEach(sj::add);

        return sj.toString();
    }

    public Set<String> getTasksList() {
        return tasksList;
    }
}
