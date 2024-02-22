package drake.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * Inherits from the {@code Task} class and includes additional information
 * specific to events, such as the start time and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a string representation of an instance of this class.
     *
     * @return The String representation of an instance of this class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
