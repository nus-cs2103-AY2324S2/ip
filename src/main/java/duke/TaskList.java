package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Constructs a TaskList object with a given ArrayList of tasks.
     *
     * @param tasks The ArrayList of tasks.
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
        int initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "Task list size should increase by 1";
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     * @throws DukeException If the index is out of range.
     */
    public Task removeTask(int index) throws DukeException {
        int initialSize = tasks.size();
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task index is out of range.");
        }
        Task removed = tasks.remove(index);
        assert tasks.size() == initialSize - 1 : "Task list size should decrease by 1";
        // Ensures that a task is actually removed
        assert removed != null : "Removed task should not be null";
        return removed;
    }

    /**
     * Gets a task from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return The task that was retrieved.
     * @throws DukeException If the index is out of range.
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task index is out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Gets a copy of the ArrayList of tasks.
     *
     * @return A copy of the ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
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
     * Finds and returns a list of tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks if a task is a duplicate of an existing task in the TaskList.
     *
     * @param task The task to be checked.
     * @return True if the task is a duplicate, and false otherwise.
     */
    public boolean isDuplicate(Task task) {
        for (Task existingTask : tasks) {
            if (existingTask.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
