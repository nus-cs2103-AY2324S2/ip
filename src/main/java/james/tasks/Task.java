package james.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     *
     * @return Task in a format suitable for saving to a file.
     */
    public String toFileFormat() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }
}
