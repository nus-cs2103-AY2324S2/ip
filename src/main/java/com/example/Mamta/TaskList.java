package com.example.Mamta;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> history = new ArrayList<Task>();

    public static void addTask(Task t) {
        history.add(t);
    }

    public static void removeTask(Task t) {
        history.remove(t);
    }

    public static ArrayList<Task> getHistory() {
        return history;
    }

}
