package homie;

import java.io.Serializable;

/**
 * Task class is a generic task that has a description and a status.
 * Provides methods to set the task's status as done or not done, and get the task's current status icon.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description This is the description of the Task in String.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of task.
     *
     * @return Returns X if task is done. Else returns a whitespace.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets a task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets a task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return this.description;
    }

}
