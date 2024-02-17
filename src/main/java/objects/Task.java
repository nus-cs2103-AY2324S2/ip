package objects;

import java.io.Serializable;

/**
 * Task is a class representing a general task.
 * It implements the Serializable interface for object serialization.
 */
public class Task implements Serializable {
    private final String name;
    private boolean isDone;

    /**
     * Constructs a Task object with a name and sets its initial status to not done.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the status icon based on whether the task is done or not.
     *
     * @return "X" if the task is done, " " (empty string) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a formatted string representation of the Task object.
     *
     * @return A string containing the task status icon and name.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task by setting its status to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    public void snooze() {};
}
