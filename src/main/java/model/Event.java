package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task with a specific time range.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@code Event} instance with the specified title, start time, and end time.
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
     * Returns a string representation of the event task, including its type, title, and deadline details.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (on: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"))
                + " to " + to.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
