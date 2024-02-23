package alastor;

import java.util.ArrayList;

import alastor.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
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
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) throws AlastorException {
        if (this.tasks.contains(task)) {
            throw new AlastorException("The task is already in the list.");
        }
        this.tasks.add(task);
        assert this.tasks.contains(task) : "Task should be added to the list";
    }

    /**
     * Returns a new TaskList containing tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return A new TaskList containing tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.isMatchingKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        this.tasks.remove(index);
        assert !this.tasks.contains(this.tasks.get(index)) : "Task should be deleted from the list";
    }
}
