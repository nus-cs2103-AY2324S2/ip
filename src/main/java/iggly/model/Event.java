package iggly.model;

import static iggly.util.DateTimeUtil.formatDateTime;
import static iggly.util.DateTimeUtil.formatTime;

import java.time.LocalDateTime;

/**
 * The {@link Event} class represents a task with a specific time range.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@link Event} instance with the specified title, start time, and end time.
     *
     * @param title The title of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its type, title, and event details.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (on: " + formatDateTime(from)
                + " to " + formatTime(to) + ")";
    }
}
