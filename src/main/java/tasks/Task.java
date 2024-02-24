package tasks;

import java.time.format.DateTimeFormatter;

/**
 * represents a task inputted by user.
 * Each task must have a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The date time formatter for parsing input dates.
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * The date time formatter for formatting output dates.
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

     /**
     * Constructs a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task ('X' if done, ' ' if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status The status of the task (true if done, false otherwise).
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new task description.
     */
    // public void setDescription(String description) {
    //     this.description = description;
    // }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
