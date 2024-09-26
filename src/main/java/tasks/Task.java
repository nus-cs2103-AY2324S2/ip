package tasks;

import java.io.Serializable;

/**
 * Creates a Task.
 */
public class Task implements Serializable, Comparable<Task> {
    private String marking;
    private String execute;

    /**
     * Constructor for Task.
     *
     * @param execute task noted
     */
    public Task(String execute) {
        this.execute = execute;
        this.marking = " ";
    }

    /**
     * Marks the task with X.
     */
    public void markIt() {
        this.marking = "X";
    }

    /**
     * Unmarks the task by leaving it blank.
     */
    public void unMark() {
        this.marking = " ";
    }

    /**
     * Gets description of the tasks.
     */
    public String getExecute() {
        return this.execute;
    }

    /**
     * Overrides toString to print the task.
     *
     * @return task and its status
     */
    @Override
    public String toString() {
        return "[" + this.marking + "] " + this.execute;
    }

    /**
     * Overrides comparison to sort alphabetically.
     *
     * @param task task to be compared to
     * @return int of the comparison
     */
    @Override
    public int compareTo(Task task) {
        return this.execute.compareToIgnoreCase(task.execute);
    }
}
