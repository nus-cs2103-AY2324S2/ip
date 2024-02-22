package homie;

import java.io.Serializable;

/**
 * Task class that contains task objects.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description This is the String description of the tasks.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return this.description;
    }

}
