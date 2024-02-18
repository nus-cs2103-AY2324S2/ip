package tasks;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import enums.TaskType;

/**
 * The Task class represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected String tag;
    protected boolean isDone;
    protected TaskType type;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US);

    /**
     * Constructs a Task instance with the specified description and initializes it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.tag = "#default";
        this.isDone = false;
    }

    /**
     * Sets the tag for this object.
     *
     * @param tag the new tag to be set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Retrieves the tag associated with this object.
     *
     * @return the tag associated with this object
     */
    public String getTag() {
        return tag;
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
     * Checks if the task is marked as completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the task to a formatted string for saving to a file.
     *
     * @return The task in a format suitable for saving to a file.
     */
    public String toFileFormat() {
        return (this.type == TaskType.TODO ? "T" : this.type == TaskType.DEADLINE ? "D" : "E")
                + " | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + tag;
    }

    /**
     * Converts the task to a string representation with completion status for displaying to the user.
     *
     * @return The string representation of the task with completion status.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + tag + " " + description;
    }
}
