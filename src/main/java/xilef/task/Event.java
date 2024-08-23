package xilef.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An {@code Event} is a type of {@code Task} with a start and end time.
 */
public class Event extends Task {
    /**
     * The start time of the {@code Event}.
     */
    protected LocalDateTime from;

    /**
     * The end time of the {@code Event}.
     */
    protected LocalDateTime to;

    /**
     * Creates a new {@code Event} with the given description, start time, and end time.
     *
     * @param description The description of the {@code Event}.
     * @param from The start time of the {@code Event}.
     * @param to The end time of the {@code Event}.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy| hh:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("d MMM yyyy| hh:mm a")) + ")";
    }

    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.from + " | " + this.to;
    }
}
