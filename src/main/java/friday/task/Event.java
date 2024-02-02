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
        this.from = from;
        this.to = to;
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Gets the formatted start time string ("MMM dd yyyy HHmm").
     *
     * @return The formatted start time string.
     */
    public String getFormattedFromTime() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Gets the formatted end time string ("MMM dd yyyy HHmm").
     *
     * @return The formatted end time string.
     */
    public String getFormattedToTime() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Event object.
     *
     * @return The formatted string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFromTime()
                        + " to: " + getFormattedToTime() + ")";
    }
}
