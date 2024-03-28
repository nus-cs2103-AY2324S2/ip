package pyrite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Task that represents an event, with a start and end date and time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event.
     *
     * @param description Description of the event.
     * @param start       Start date and time of the event.
     * @param end         End date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    /**
     * Generate a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + ")";
    }
}


