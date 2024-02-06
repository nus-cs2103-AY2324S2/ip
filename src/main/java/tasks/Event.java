package tasks;

import java.time.LocalDate;

import enums.TaskType;


/**
 * The Event class represents a task of type "Event" with a description, start date, and end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event instance with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = TaskType.EVENT;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Converts the event to a formatted string for saving to a file.
     *
     * @return The event in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }

    /**
     * Converts the event to a string representation with task type, completion status, and date range.
     *
     * @return The string representation of the event with task type, completion status, and date range.
     */
    @Override
    public String toString() {
        return type.getSymbol() + super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}
