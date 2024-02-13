package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initializes a Deadline object with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task in LocalDate format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        assert by != null : "Deadline should not be null";
        this.by = by;
    }

    /**
     * Returns the type of the task.
     *
     * @return task type
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the date of the task in LocalDate object.
     *
     * @return date of the task
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns all the information of the task in String format.
     *
     * @return date of the task in String format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
