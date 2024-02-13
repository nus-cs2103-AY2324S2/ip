package yoda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime FROM; // Event starting time
    private final LocalDateTime TO; // Event ending time

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description A string describing the event.
     * @param from A string representing the start time of the event.
     * @param to A string representing the end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description); // Calls the constructor of the superclass Task
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return FROM;
    }

    /**
     * Retrieves the start time of the event as a string.
     *
     * @return The start time of the event as a string.
     */
    public String getFromString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return getFrom().format(formatter);
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return TO;
    }
    /**
     * Retrieves the end time of the event as a string.
     *
     * @return The end time of the event as a string.
     */
    public String getToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return getTo().format(formatter);
    }

    /**
     * Returns a string representation of the event, including its type,
     * @return
     */
    @Override
    public String toFileFormatDetails() {
        // Event-specific format
        return getDescription() + " | " + getFromString() + " to " + getToString();
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
        return "[" + getType() + "]" + super.toString() + " (from: " + FROM.format(formatter) + " to: " + TO.format(formatter) + ")";
    }
}
