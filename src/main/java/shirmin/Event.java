package shirmin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event, represented by a description string and two LocalDateTime objects - from and to
 *
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event object with a description, start time, and end time.
     * <p>
     * The start and end times are parsed from strings into LocalDateTime objects.
     * If parsing fails, both 'from' and 'to' are set to null.
     *
     * @param description Description of the event.
     * @param from String representing the start time of the event, expected in "yyyy-MM-dd HHmm" format.
     * @param to String representing the end time of the event, expected in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            // System.err.println("Error parsing event dates: " + from + " and " + to + ". Please use the format 'yyyy-MM-dd HHmm'.");
            this.from = null; // Set to null in case of parsing error
            this.to = null;   // Set to null in case of parsing error
        }
    }

    /**
     * Returns a string representation of the start time of the event.
     * <p>
     * The format of the returned string is "MMM dd yyyy HH:mm".
     *
     * @return Formatted string of the 'from' LocalDateTime field, or null if 'from' is null.
     */
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns a string representation of the end time of the event.
     * <p>
     * The format of the returned string is "MMM dd yyyy HH:mm".
     *
     * @return Formatted string of the 'to' LocalDateTime field, or null if 'to' is null.
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
    /**
     * Checks if both start date and end date are set for an event.
     * <p>
     * This method returns a Boolean value indicating whether both 'from' and 'to'
     * LocalDateTime fields in the current object instance are instantiated (not null).
     * It helps in determining if the event has a valid date range.
     *
     * @return true if both 'from' and 'to' date-time fields are non-null, false otherwise
     */
    public Boolean hasValidDates() {
        return this.from != null && this.to != null;
    }

    /**
     * Returns a string representation of the Event.
     * <p>
     * The format includes the type of task ('[E]'), the description, and the formatted 'from' and 'to' times.
     * Example: "[E][✓] Meeting (from: Oct 12 2021 10:00 to: Oct 12 2021 11:00)"
     *
     * @return String representing the Event with its status, description, and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
