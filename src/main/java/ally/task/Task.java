package ally.task;

import java.io.Serializable;

/**
 * Abstraction of Tasks.
 * Inherits from Serializable to store tasks into binary files.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task with given name.
     * Task is incomplete by default.
     *
     * @param description name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates Task.
     * Another Task Constructor.
     * To be used only by Child Classes of Task.
     */
    protected Task() {
    }

    /**
     * Gets Status Icon of Task
     * "X" denotes completion and " " denotes incomplete
     * @return String status icon
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Gets String representaiton of task.
     * @return String string representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as complete.
     */
     public void markComplete() {
        this.isDone = true;
     }

    /**
     * Marks the task as incomplete.
     */
    public void unmarkComplete() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }
}
