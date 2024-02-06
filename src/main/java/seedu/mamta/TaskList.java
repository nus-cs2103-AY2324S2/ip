package seedu.mamta;

import java.util.ArrayList;

/**
 * Manages the list of tasks.
 */
public class TaskList {

    /**
     * List to store the history of tasks.
     */
    private static ArrayList<Task> history = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param t The task to be added.
     */
    public static void addTask(Task t) {
        history.add(t);
    }

    /**
     * Removes a task from the list.
     * @param t The task to be removed.
     */
    public static void removeTask(Task t) {
        history.remove(t);
    }

    /**
     * Retrieves the history of tasks.
     * @return The list of tasks.
     */
    public static ArrayList<Task> getHistory() {
        return history;
    }

    /**
     * Clears the list of tasks.
     */
    public static void clear() {
        TaskList.history = new ArrayList<>();
    }
}
