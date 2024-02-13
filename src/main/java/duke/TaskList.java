package duke;

import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasklist; // List to store tasks

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        tasklist = tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param n The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int n) {
        return tasklist.get(n);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        tasklist.add(t);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param num The index of the task to mark as done.
     */
    public void mark(int num) {
        tasklist.get(num).mark();
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param num The index of the task to unmark.
     */
    public void unmark(int num) {
        tasklist.get(num).unmark();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param num The index of the task to delete.
     */
    public void delete(int num) {
        tasklist.remove(num);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int length() {
        return tasklist.size();
    }

    /**
     * Iterates through the task list and prints each task along with its index.
     */
    public String iterateout() {
        String output = "";
        for (int i = 0; i < tasklist.size(); i++) {
            output = output.concat((i + 1) + ". " + tasklist.get(i));
            output = output.concat("\n");
        }
        return output;
    }
}
