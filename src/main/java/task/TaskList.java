package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList object with the given tasks.
     *
     * @param tasks The tasks to be added to the list.
     */
    public TaskList(Task... tasks) {
        this.list = new ArrayList<>();
        this.list.addAll(Arrays.asList(tasks));
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        this.list.remove(index - 1);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index - 1);
    }

    public String getSavedList() {
        StringBuilder sb = new StringBuilder();
        this.list.forEach((Task element) -> sb.append(element.encode()));
        return sb.toString();
    }

    public String getList() {
        StringBuilder sb = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        this.list.forEach((Task element) -> sb.append(index.getAndIncrement() + ". " + element.toString()));
        return sb.toString();
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return a string representation of the number of tasks in the list
     */
    public String getSize() {
        return "Now you have " + list.size() + " tasks in the list.";
    }

    public int size() {
        return list.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        this.list.get(index - 1).markAsDone();
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        this.list.get(index - 1).markAsUndone();
    }
}
