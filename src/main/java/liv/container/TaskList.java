package liv.container;

import java.util.ArrayList;

import liv.task.Task;

/**
 * A class that stores all the tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor of the class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the current size of the task list.
     * @return The size of the task list.
     */
    public static int getListSize() {
        return tasks.size();
    }

    /**
     * Gets the task at a specific index.
     * @param index The index to retrieve the task from.
     * @return The task at the specified index.
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the end of list.
     * @param task The task to be added to the list.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task to be removed.
     * @return The task that was removed from the list.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }
}
