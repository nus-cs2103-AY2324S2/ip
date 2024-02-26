package dukio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a deadline.
 * It is a subclass of Task, with a deadline date.
 */
public class Deadline extends Task {

    LocalDate byWhen;

    /**
     * Constructor for the deadline.
     *
     * @param description The description of the deadline.
     * @param byWhen      The end date of the deadline.
     * @param isDone        The status of the deadline.
     */
    public Deadline(String description, LocalDate byWhen, boolean isDone) {
        super(description, isDone);
        this.byWhen = byWhen;
    }

    /**
     * Returns the end date of the deadline.
     *
     * @return The end date of the deadline.
     */
    public LocalDate getByWhen() {
        return byWhen;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
