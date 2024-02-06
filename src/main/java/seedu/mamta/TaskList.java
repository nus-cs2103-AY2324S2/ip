package seedu.mamta;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> history = new ArrayList<Task>();

    public static void addTask(Task t) {
        history.add(t);
    }

    public static void removeTask(Task t) {
        history.remove(t);
    }

    public static ArrayList<Task> getHistory() {
        return history;
    }

    public static void clear() {
        TaskList.history = new ArrayList<Task>();
    }
}
