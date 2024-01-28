package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Stores information regarding tasks to be recorded.
 *
 * @author KohGuanZeh
 */
public abstract class Task {
    // Input format for all datetime inputs.
    public static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");
    // Output format for all datetime outputs.
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.FULL, FormatStyle.SHORT);
    // Task description.
    private String description;
    // Task completion status.
    private boolean isDone;

    /**
     * Creates a new task with given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the task and its completion status.
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task completion status and description.
     */
    public String getTaskInformation() {
        String marker = this.isDone ? "[X]" : "[ ]";
        return marker + " " + this.description;
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    public abstract String toDataString();
}
