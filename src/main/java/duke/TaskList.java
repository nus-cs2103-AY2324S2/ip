package duke;

import java.util.ArrayList;

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
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     * @throws DukeException If the index is out of range.
     */
    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task index is out of range.");
        }
        return tasks.remove(index);
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
}
