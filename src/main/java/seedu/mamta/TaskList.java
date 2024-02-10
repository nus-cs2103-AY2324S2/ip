package seedu.mamta;

import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * Manages the list of tasks.
 */
public class TaskList {

    /**
     * List to store the history of tasks.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param t The task to be added.
     */
    public static void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     * @param t The task to be removed.
     */
    public static void removeTask(Task t) {
        tasks.remove(t);
    }

    /**
     * Retrieves the history of tasks.
     * @return The list of tasks.
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Clears the list of tasks.
     */
    public static void clear() {
        TaskList.tasks = new ArrayList<>();
    }

    public static String searchOutput(String keyword) {
        return tasks.stream()
                .filter(task -> task.getContent().contains(keyword))
                .map(task -> String.format("%d. %s\n", tasks.indexOf(task) + 1, task))
                .collect(Collectors.joining());
    }

}
