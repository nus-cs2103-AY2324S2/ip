// Event.java
package bentley;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with a specific start and end date and
 * time.
 * Extends the Task class.
 */
public class Event extends Task {

    /**
     * The start date and time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end date and time of the event.
     */
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the given description, start date, and end
     * date.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event in the format
     *                    "yyyy-MM-dd HHmm".
     * @param to          The end date and time of the event in the format
     *                    "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        // Parse input dates with both date and time components
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a formatted string representation of the Event object.
     *
     * @return A string containing task type, description, start date, and end date.
     */
    @Override
    public String toString() {
        // Format dates in MMM dd yyyy hh:mma format
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));

        return "E |" + super.toString() + " |" + " from: " + formattedFrom + "  to: " + formattedTo;
    }
}
