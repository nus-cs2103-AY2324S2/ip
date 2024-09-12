package judy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs within a specified time range.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets a formatted string representation of the date and time.
     *
     * @param dateTime The date and time to be formatted.
     * @return The formatted string representation of the date and time.
     */
    private String getDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(pattern);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        String fromDateTime = getDateTimeString(from);
        String toDateTime = getDateTimeString(to);
        return " [E]" + super.toString() + " (from: " + fromDateTime + " to: " + toDateTime + ")";
    }

    /**
     * Encodes the Event task as a string for storage.
     *
     * @return The encoded string representation of the Event task.
     */
    @Override
    public String encode() {
        String fromDateTime = getDateTimeString(from);
        String toDateTime = getDateTimeString(to);
        return " E" + super.encode() + " | " + fromDateTime + " - " + toDateTime;
    }

}
