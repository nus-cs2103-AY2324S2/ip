package homie;

import java.io.Serializable;

/**
 * Task class that contains task objects.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description This is the String description of the tasks.
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
