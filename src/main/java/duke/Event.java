package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs within a specific time range.
 * It extends the Task class and adds functionality to store and display the start and end times of the event.
 * Event is a subclass of {@link Task}, inheriting its properties and methods
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    private LocalDateTime from;

    /**
     * The end time of the event.
     */
    private LocalDateTime to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description A string representing the description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    LocalDateTime getTo() {
        return to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    LocalDateTime getFrom() {
        return from;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = from.format(outputFormatter);
        String formattedTo = to.format(outputFormatter);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedFrom, formattedTo);
    }
}
