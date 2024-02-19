package hal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String DIVIDER = " | ";

    /**
     * Constructs a new Deadline object.
     *
     * @param isDone      Indicates if the task is done.
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(boolean isDone, String description, String by) {
        this.isDone = isDone;
        this.description = description;
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    /**
     * Returns a string representing the task suitable for saving to a file.
     *
     * @return A string representing the task for file storage.
     */
    @Override
    public String getFileString() {
        return "D" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description + DIVIDER + by;
    }

}
