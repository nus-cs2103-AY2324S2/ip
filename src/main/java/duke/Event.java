package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event task that handles start and end date of the event.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initializes an Event with the description, start and end date of the event.
     *
     * @param description the description of the event
     * @param from the start date of the event in LocalDate format
     * @param to the end date of the event in LocalDate format
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        assert from != null : "Start date should not be null";
        assert to != null : "End date should not be null";
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
    public LocalDate getFrom() {
        return from;
    }
    public LocalDate getTo() {
        return to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            + " to: "
            + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
