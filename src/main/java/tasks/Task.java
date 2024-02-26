package tasks;

import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

/**
 * represents a task inputted by user.
 * Each task must have a description and completion status.
 */
public abstract class Task {
    /**
     * The date time formatter for formatting output dates.
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    /**
     * The date time formatter for parsing input dates.
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = StringUtils.normalizeSpace(description);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return StringUtils.deleteWhitespace(description)
                .equals(StringUtils.deleteWhitespace(task.description));
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
