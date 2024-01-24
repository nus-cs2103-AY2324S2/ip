package model;

import java.io.Serializable;

/**
 * The {@code Task} class is an abstract base class representing a generic task in the Duke application.
 * It implements the {@code Serializable} interface to support object serialization.
 */
public abstract class Task implements Serializable {
    private final String title;
    private boolean isDone;

    /**
     * Constructs a {@code Task} instance with the specified title. By default, the task is marked as not done.
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

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}
