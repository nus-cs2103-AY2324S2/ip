package zack.util;

import zack.ZackException;
import zack.tasks.Task;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws ZackException If the index is out of range.
     */
    public Task deleteTask(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("Task index is out of range.");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws ZackException If the index is out of range.
     */
    public Task getTask(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("Task index is out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Gets all tasks in the TaskList.
     *
     * @return An ArrayList of all tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }
}
