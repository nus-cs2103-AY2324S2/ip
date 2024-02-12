package drake.task;

import java.io.Serializable;

/**
 * Represents a general task. This class is intended to be a superclass for various types of tasks
 * such as todos, deadlines, and events. It implements Serializable so that its instances
 * can be serialized into a file for persistent storage.
 */
public class Task implements Serializable { // adapted skeleton from cs2103t course website
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
