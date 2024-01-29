package tasks;

import java.io.Serializable;

/**
 * Action class
 */
public class Task implements Serializable {
    private String marking;
    private String execute;

    /**
     * Constructor for Actions1.Action (User input their task)
     *
     * @param execute task noted
     */
    public Task(String execute) {
        this.execute = execute;
        this.marking = " ";
    }

    /**
     * Mark the task with X
     */
    public void markIt() {
        this.marking = "X";
    }

    /**
     * Unmark the task by leaving it blank
     */
    public void unMark() {
        this.marking = " ";
    }

    /**
     * Override toString to print the task
     *
     * @return task and its status
     */
    @Override
    public String toString() {
        return "[" + this.marking + "] " + this.execute;
    }
}
