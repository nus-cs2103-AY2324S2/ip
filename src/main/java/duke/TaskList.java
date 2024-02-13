package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks in the Duke application.
 *
 * @author Qin Boan
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}



