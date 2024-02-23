package ficin.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks that can be managed and manipulated.
 * It provides methods for adding, retrieving, marking as done, unmarking, and deleting tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
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
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task as done in the TaskList by its index.
     *
     * @param index The index of the task to be marked as done, 1-based.
     * @return The task that was marked as done, or null if the index is invalid.
     */
    public Task markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            Task task = this.tasks.get(index - 1); // Adjust for 0-based indexing
            task.markAsDone();
            return task;
        }
        return null;
    }

    /**
     * Unmarks a task as done in the TaskList by its index.
     *
     * @param index The index of the task to be unmarked, 1-based.
     * @return The task that was unmarked, or null if the index is invalid.
     */
    public Task unmarkTaskDone(int index) {
        if (isValidIndex(index)) {
            Task task = this.tasks.get(index - 1); // Adjust for 0-based indexing
            task.markAsNotDone();
            return task;
        }
        return null;
    }

    /**
     * Deletes a task from the TaskList by its index.
     *
     * @param index The index of the task to be deleted, 1-based.
     * @return The task that was deleted, or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (isValidIndex(index)) {
            return this.tasks.remove(index - 1); // Adjust for 0-based indexing
        }
        return null;
    }

    /**
     * Checks if the given index is a valid index for accessing tasks in the TaskList.
     *
     * @param taskNum The index to be validated, 1-based.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int taskNum) {
        return taskNum >= 1 && taskNum <= this.tasks.size();
    }
}
