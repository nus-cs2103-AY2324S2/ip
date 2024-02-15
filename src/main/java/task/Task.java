package task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public class Task {
    /**
     * Format for representing datetime as string.
     */
    public static final DateTimeFormatter DATE_TIME_STRING_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    protected String description;
    protected boolean isDone = false;

    /**
     * Creates a task object with specified task description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns status icon for marking or unmarking tasks.
     *
     * @return Icon representing whether task has been marked or unmarked.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns task as a string to be written into storage file.
     *
     * @return Task in string format to be written into storage file.
     */
    public String toWritableString() {
        return isDone + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
