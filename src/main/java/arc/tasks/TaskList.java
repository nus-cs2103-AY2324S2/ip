package arc.tasks;

import java.util.ArrayList;

import arc.exceptions.ArcException;
import arc.exceptions.tasks.MissingTaskException;

/**
 * Represents a list of tasks in the Arc application.
 * This class encapsulates an ArrayList of Task objects, providing methods
 * to manipulate and access the tasks.
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
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws ArcException If the index is out of bounds, indicating the task does not exist.
     */
    public Task get(int index) throws ArcException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new MissingTaskException();
        }
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to set.
     * @param task The task to set.
     * @return The task at the specified index.
     * @throws ArcException If the index is out of bounds, indicating the task does not exist.
     */
    public Task set(int index, Task task) throws ArcException {
        try {
            return this.tasks.set(index, task);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new MissingTaskException();
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws ArcException If the index is out of bounds, indicating the task cannot be removed.
     */
    public Task remove(int index) throws ArcException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new MissingTaskException();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }
}
