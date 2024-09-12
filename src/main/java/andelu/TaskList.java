package andelu;

import java.util.ArrayList;

import task.Task;

/**
 * A class that contains all the tasks.
 * Performs the task job.
 */
public class TaskList {

    /** The list of tasks. */
    private static ArrayList<Task> tasks;

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a list of tasks from the argument.
     * @param tasks the list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Changes the list of tasks.
     *
     * @param tasks the list of tasks to replace.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new Task to the list of Tasks.
     *
     * @param task new Task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves an individual task based on the index.
     *
     * @param index the selection.
     * @return the selected task.
     */
    public Task getIndividualTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task from the list of tasks.
     *
     * @param index the selection.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks the task as completed.
     *
     * @param index the selection.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the task as not complete.
     *
     * @param index the selection.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsUndone();
    }
}
