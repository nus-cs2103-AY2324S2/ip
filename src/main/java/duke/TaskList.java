package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * The `TaskList` class represents a task list.
 * It provides methods to store and modify tasks.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructor of a task list that stores existing tasks.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of a task list that stores existing tasks with some given tasks.
     *
     * @param tasks A list of existing tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the task list.
     *
     * @param task A task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the stored tasks in an ArrayList.
     *
     * @return Stored tasks.
     */
    public List<Task> getItems() {
        return tasks;
    }

    /**
     * Returns a specific task in the task list by index.
     *
     * @param index The index of the task in the task list.
     * @return The task with the specified index in the task list.
     */
    public Task getItem(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a specific task in the task list as done.
     *
     * @param index The index of the task in the task list.
     */
    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks a specific task in the task list as not done.
     *
     * @param index The index of the task in the task list.
     */
    public void unmarkDone(int index) {
        tasks.get(index).unmarkDone();
    }

    /**
     * Marks a specific task in the task list as not done.
     *
     * @param index The index of the task in the task list.
     */
    public void addTag(int index, String tag) {
        tasks.get(index).addTag(tag);
    }

    /**
     * Deletes a specific task in the task list.
     *
     * @param index The index of the task in the task list.
     */
    public void delete(int index) {
        tasks.remove(index);
    }
}
