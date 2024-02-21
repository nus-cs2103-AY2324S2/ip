package linus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a By date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of deadline task.
     * @param by Completion date of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the output or String representation of the Deadline Task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadlineDate() + ")";
    }

    /**
     * Returns the By date.
     *
     * @return By date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Formats the deadline date into Month Day Year format.
     *
     * @return Formatted String representation of By date.
     */
    private String formatDeadlineDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}