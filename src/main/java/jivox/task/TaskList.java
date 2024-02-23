package jivox.task;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList represents a list of tasks.
 */
public class TaskList {


    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList with the given initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param taskNum The index of the task to delete.
     */
    public void delete(int taskNum) {
        this.tasks.remove(taskNum);
    }

    /**
     * Gets the number of tasks in this list.
     *
     * @return The number of tasks.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * returns the List of tasks available
     *
     * @return the List of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds the given task to this list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Gets the task at the given index.
     *
     * @param taskNum The index of the task to get.
     * @return The task.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum);
    }
}
