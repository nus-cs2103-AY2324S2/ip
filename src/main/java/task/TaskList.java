package task;

import java.util.ArrayList;

/**
 * Represents a TaskList used to store Tasks.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a TaskList object with pre-existing ArrayList of Tasks.
     *
     * @param taskList ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes Task from TaskList.
     *
     * @param task Task to be deleted.
     */
    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Retrieves a Task from TaskList.
     *
     * @param taskIndex Index of task to be retrieved.
     * @return Task specified by index.
     */
    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Returns number of Tasks in TaskList.
     *
     * @return Number of Tasks.
     */
    public int size() {
        return this.taskList.size();
    }
}
