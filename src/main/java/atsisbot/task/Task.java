package atsisbot.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a atsisbot.task with a description and status.
 */
public abstract class Task {
    protected String description;
    protected TaskStatus status;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructs a Task object with the given description.
     * The initial status is set to TaskStatus.UNDONE.
     * The atsisbot.task number is incremented.
     * 
     * @param description the description of the atsisbot.task
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.UNDONE;
    }

    /**
     * Returns the status of the atsisbot.task.
     * 
     * @return the status of the atsisbot.task
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Marks the atsisbot.task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks the atsisbot.task as undone.
     */
    public void markAsUndone() {
        this.status = TaskStatus.UNDONE;
    }

    /**
     * Returns the description of the atsisbot.task.
     * 
     * @return the description of the atsisbot.task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the atsisbot.task.
     * 
     * @return a string representation of the atsisbot.task
     */
    public abstract String toString();

    /**
     * Encodes a atsisbot.task.
     */
    public abstract String encode();
}
