package task;

import java.util.LinkedList;

/**
 * Represents a list of tasks to be stored.
 */
public class TaskList {
    private LinkedList<Task> tasks;

    /**
     * Creates an instance of TaskList containing tasks.
     * 
     * @param tasks List of tasks to be stored upon instantiating.
     */
    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Get a task from the list. User-input is 1-indexed.
     */
    public Task get(int i) {
        return tasks.get(i - 1);
    }

    /**
     * Adds the task into the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list. User-input is 1-indexed.
     */
    public Task remove(int indexOfTask) {
        Task task = tasks.remove(indexOfTask - 1);
        return task;
    }

    public LinkedList<Task> getList() {
        return tasks;
    }
}
