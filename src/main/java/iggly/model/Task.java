package iggly.model;

import java.io.Serializable;

/**
 * The {@link Task} class is an abstract base class representing a generic task in the iggly.Duke application.
 * It implements the {@link Serializable} interface to support object serialization.
 */
public abstract class Task implements Serializable {
    private final String title;
    private boolean isDone;

    /**
     * Constructs a {@link Task} instance with the specified title. By default, the task is marked as not done.
     *
     * @param title The title of the task.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status (done or not) and title.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.title;
        } else {
            return "[ ] " + this.title;
        }
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks whether the task associated with this object has been completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the title of the task.
     *
     * @return the title of the task.
     */
    public String getTitle() {
        return this.title;
    }
}
