package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list of tasks. This class is responsible for managing tasks,
 * including adding, deleting, and completing tasks, as well as retrieving
 * information about the tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with a pre-existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Initial task list cannot be null";
        this.tasks = new ArrayList<>(tasks); // Use a copy of the list to avoid modifying the original list
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     * Indexing for the user starts from 1, so 1 is subtracted for internal zero-based indexing.
     *
     * @param taskIndex The one-based index of the task to be removed.
     */
    public void deleteTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index is out of bounds";
        tasks.remove(taskIndex);
    }

    /**
     * Marks a task at the specified index as completed.
     * Indexing for the user starts from 1, so 1 is subtracted for internal zero-based indexing.
     *
     * @param taskIndex The one-based index of the task to be marked as done.
     */
    public void completeTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index is out of bounds";
        Task task = tasks.get(taskIndex);
        task.markAsDone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks; // Returns direct reference; consider returning an unmodifiable list for encapsulation
    }

    /**
     * Retrieves a task by its one-based index in the list.
     *
     * @param index The one-based index of the task.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index is out of bounds";
        return tasks.get(index);
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.isBlank() : "Search keyword cannot be null or blank";
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
