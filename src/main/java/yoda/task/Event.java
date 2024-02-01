package yoda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime from; // Event starting time
    private final LocalDateTime to; // Event ending time

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description A string describing the event.
     * @param from A string representing the start time of the event.
     * @param to A string representing the end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description); // Calls the constructor of the superclass Task
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Retrieves the start time of the event as a string.
     *
     * @return The start time of the event as a string.
     */
    public String getFromString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return this.from.format(formatter);
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }
    /**
     * Retrieves the end time of the event as a string.
     *
     * @return The end time of the event as a string.
     */
    public String getToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return this.to.format(formatter);
    }
    /**
     * Returns a string representation of the event.
     *
     * @return A formatted string with the type of task, its description,
     *         start time, and end time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
