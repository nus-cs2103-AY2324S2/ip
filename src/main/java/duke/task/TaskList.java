package duke.task;

import java.util.ArrayList;

/**
 * A {@code TaskList} is a list of {@code Task} objects.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Creates a new {@code TaskList} with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a new {@code TaskList} with the given list of tasks.
     *
     * @param list The list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index The index of the task to remove.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    public void remove(Task task) {
        this.tasks.remove(task);
    }
    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }
}
