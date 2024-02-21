package linus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an event task.
     *
     * @param description Description of the event.
     * @param from From date.
     * @param to To date.
     * @param isDone Whether the event is completed or not.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Decides the output or String representation.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatEventDate(from) + " to: " + formatEventDate(to) + ")";
    }

    /**
     * Returns the date event starts.
     *
     * @return From date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the date event ends.
     *
     * @return To date.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Formats the event date into Month Day Year format.
     *
     * @param dateTime Event date.
     * @return Formatted String representation of By date.
     */
    private String formatEventDate(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}