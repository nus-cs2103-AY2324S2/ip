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

    /**
     * Gets status icon
     * @return Status icon based on isDone field.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Creates a string representation of an instance of this class.
     *
     * @return The String representation of an instance of this class.
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
