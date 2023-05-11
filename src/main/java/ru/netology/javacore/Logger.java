package ru.netology.javacore;

import java.util.Deque;
import java.util.LinkedList;

public class Logger {

    private static Logger instance;

    private Deque<Task> tasksList;

    private Logger() {
        this.tasksList = new LinkedList<>();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public Task getLastTask() {
        return tasksList.pollLast();
    }

    public void addTask(Task task) {
        tasksList.offerLast(task);
    }
}
