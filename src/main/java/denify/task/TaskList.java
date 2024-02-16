package denify.task;

import java.util.ArrayList;

import denify.exception.DenifyException;
import denify.storage.Storage;

/**
 * The `TaskList` class represents a list of tasks in Denify.
 */
public class TaskList {
    /**
     * The `ArrayList` that stores the tasks.
     */
    private final ArrayList<Task> tasks;
    /**
     * Constructs a `TaskList` with the given `ArrayList` of tasks.
     *
     * @param tasks The `ArrayList` of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Constructs an empty `TaskList`.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Saves the tasks in the `TaskList` to the provided `Storage`.
     *
     * @param storage The `Storage` object to save tasks to.
     * @throws DenifyException If there is an issue saving tasks to storage.
     */
    public void saveToStorage(Storage storage) throws DenifyException {
        storage.saveTasks(tasks);
    }
    /**
     * Retrieves the `ArrayList` of tasks from the `TaskList`.
     *
     * @return The `ArrayList` of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
    /**
     * Adds a task to the `TaskList`.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Deletes a task from the `TaskList` by index.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return The deleted task.
     * @throws DenifyException If the specified task index is out of bounds.
     */
    public Task deleteTask(int taskIndex) throws DenifyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            return tasks.remove(taskIndex);
        } else {
            throw new DenifyException("Task not found.");
        }
    }
    /**
     * Marks a task as done by index.
     *
     * @param taskIndex The index of the task to be marked as done.
     * @return The task marked as done.
     * @throws DenifyException If the specified task index is out of bounds.
     */
    public Task markTask(int taskIndex) throws DenifyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.setDone(true);
            return task;
        } else {
            throw new DenifyException("Task not found.");
        }
    }
    /**
     * Marks a task as not done by index.
     *
     * @param taskIndex The index of the task to be marked as not done.
     * @return The task marked as not done.
     * @throws DenifyException If the specified task index is out of bounds.
     */
    public Task unmarkTask(int taskIndex) throws DenifyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.setDone(false);
            return task;
        } else {
            throw new DenifyException("Task not found.");
        }
    }
}
