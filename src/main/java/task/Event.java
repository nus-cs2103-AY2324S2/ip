package task;

import java.time.LocalDateTime;

/**
 * Represents an event type task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event object with description, start time and end time.
     *
     * @param description Description of event.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    @Override
    public String toWritableString() {
        return "E | " + super.toWritableString() + " | "
                + from.format(DATE_TIME_STRING_FORMAT) + " | "
                        + to.format(DATE_TIME_STRING_FORMAT);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DATE_TIME_STRING_FORMAT)
                        + " to: " + to.format(DATE_TIME_STRING_FORMAT) + ")";
    }
}
