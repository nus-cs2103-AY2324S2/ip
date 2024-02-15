package friday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Friday application.
 * Subclass of the Task class.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the specified description, start time, and end time in string format.
     *
     * @param description   The description of the event task.
     * @param fromString    The start time of the event in string format ("yyyy-MM-dd HHmm").
     * @param toTimeString  The end time of the event in string format ("yyyy-MM-dd HHmm").
     */
    public Event(String description, String fromString, String toTimeString) {
        super(description);
        assert description != null : "Description must not be null";
        assert fromString != null : "Start time string must not be null";
        assert toTimeString != null : "End time string must not be null";
        this.from = parseDateTime(fromString);
        this.to = parseDateTime(toTimeString);
    }

    /**
     * Constructs an Event object with the specified description, start time, and end time in LocalDateTime format.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event in LocalDateTime format.
     * @param to          The end time of the event in LocalDateTime format.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert description != null : "Description must not be null";
        assert from != null : "Start time must not be null";
        assert to != null : "End time must not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        assert dateTimeString != null : "Date-time string must not be null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Gets the formatted start time string ("MMM dd yyyy HHmm").
     *
     * @return The formatted start time string.
     */
    public String getFormattedFromTime() {
        assert from != null : "Start time must be initialized";
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Gets the formatted end time string ("MMM dd yyyy HHmm").
     *
     * @return The formatted end time string.
     */
    public String getFormattedToTime() {
        assert to != null : "End time must be initialized";
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Event object.
     *
     * @return The formatted string representation of the Event object.
     */
    @Override
    public String toString() {
        assert from != null : "Start time must be initialized";
        assert to != null : "End time must be initialized";
        return "[E]" + super.toString() + " (from: " + getFormattedFromTime()
                        + " to: " + getFormattedToTime() + ")";
    }
}
