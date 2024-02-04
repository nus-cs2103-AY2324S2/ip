package lrbg.codriver.data;

import java.time.LocalDate;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /** The start date of the event. */
    private final LocalDate from;
    /** The end date of the event. */
    private final LocalDate to;

    /**
     * Constructor for Event.
     * @param description The description of the task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from
                + " to: " + this.to + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + this.from + "~" + this.to;
    }
}