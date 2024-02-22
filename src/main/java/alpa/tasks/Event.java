package alpa.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        assert description != null : "Description cannot be empty";
        assert start != null : "Start time cannot be empty";
        assert end != null : "End time cannot be empty";
        assert end.isAfter(start) : "End time must be after start time";

        this.start = start;
        this.end = end;
    }

    /**
     * Returns the event task formatted for saving to a file.
     * @return The formatted string representation of the event task.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | "
             + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
             + " - " + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the string representation of the event task.
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[ E ]" + super.toString() + " (from: "
            + start.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"))
            + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
