package floofy.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a task.
 */
public class Task {

    /** The description of the task */
    protected String description;

    /** The status of the task */
    protected boolean isDone;

    /**
     * Constructs a new object of the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     */
    public String getType() {
        return "";
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Converts the date from LocalDate to String format.
     *
     * @param date The date representing dates associated with a task.
     * @return The date in a string format.
     */
    public String getDateString(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    /**
     * Marks the task as done. Updates isDone to true.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone. Updates isDone to false.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns true if the description of the task contains the keyword
     *
     * @param keyword The keyword to search for in the description.
     */
    public boolean hasMatchingDescription(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns the String of a task in its file format.
     */
    public String toFileFormat() {
        return String.format("%s | %d | %s", this.getType(), this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the String form of a task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
