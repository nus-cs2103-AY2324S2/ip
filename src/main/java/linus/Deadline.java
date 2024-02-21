package linus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task.
 * A deadline has a By date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a deadline task.
     *
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadlineDate() + ")";
    }

    /**
     *
     * @return
     */
    public LocalDate getBy() {
        return this.by;
    }

    private String formatDeadlineDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}