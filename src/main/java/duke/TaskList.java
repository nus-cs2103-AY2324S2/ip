package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Duke application.
 * Provides methods to manipulate the tasks list.
 */
public class TaskList {

    private List<Task> tasks; // The list of tasks
    public static int storageFill = 0; // A counter for the number of tasks

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Removes a task from the list at the specified index.
     * Also decrements the static storageFill counter.
     *
     * @param index The index of the task to be removed.
     */
    protected void deleteTask(int index) {
        tasks.remove(index);
        TaskList.storageFill--; // Update task count
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    protected Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    protected List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints all tasks in the list to the console.
     * Each task is printed with its list index.
     */
    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < TaskList.storageFill; i++) {
            String formattedOutput = String.format("\t%d. %s", (i + 1), this.tasks.get(i));
            System.out.println(formattedOutput);
        }
    }

    /**
     * Adds a task to the list and increments the static storageFill counter.
     *
     * @param task The task to add to the list.
     */
    protected void addTask(Task task) {
        tasks.add(task);
        storageFill++;
    }
}
