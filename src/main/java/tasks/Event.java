package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has both a starting and ending date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Instantiates Event object with description, starting and ending date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Instantiates Deadline object with description, starting date, ending date and status.
     */
    public Event(String description, LocalDate from, LocalDate to, String status) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     * Prepends the task type and appends the starting and ending date of this object.
     */
    @Override
    public String toSaveFormat() {
        return "E " + super.toSaveFormat() + " /from " + from + " /to " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
