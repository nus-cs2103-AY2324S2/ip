/**
 * The TaskList class represents a list of tasks that can be managed and manipulated.
 * It provides methods for adding, retrieving, marking as done, unmarking, and deleting tasks.
 */
package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks stored in the TaskList.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
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

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        }
        return null;
    }

    /**
     * Marks a task as done in the TaskList by its index.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that was marked as done, or null if the index is invalid.
     */
    public Task markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return task;
        }
        return null;
    }

    /**
     * Unmarks a task as done in the TaskList by its index.
     *
     * @param index The index of the task to be unmarked.
     * @return The task that was unmarked, or null if the index is invalid.
     */
    public Task unmarkTask(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index - 1);
            task.unmarkAsDone();
            return task;
        }
        return null;
    }

    /**
     * Deletes a task from the TaskList by its index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted, or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (isValidIndex(index)) {
            return tasks.remove(index - 1);
        }
        return null;
    }

    /**
     * Checks if the given index is a valid index for accessing tasks in the TaskList.
     *
     * @param index The index to be validated.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
}
