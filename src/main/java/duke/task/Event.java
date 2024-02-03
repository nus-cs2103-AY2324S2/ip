package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents an event task with a start and end time, which is a subclass of Task.
 */
public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time in the format "dd/MM/yyyy HHmm".
     * @param to          The end time in the format "dd/MM/yyyy HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    /**
     * Constructs an Event task with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from        The start time in the format "dd/MM/yyyy HHmm".
     * @param to          The end time in the format "dd/MM/yyyy HHmm".
     * @param isDone      True if the event is completed, false otherwise.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    /**
     * Parses the date and time from the given string.
     *
     * @param dateTime The date and time in the format "dd/MM/yyyy HHmm".
     * @return The parsed LocalDateTime.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return this.fromDateTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return this.toDateTime;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string in the format "[E][Status] Description (from: Start Time to: End Time)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Event task for saving to a file.
     *
     * @return A string in the format "E | Status | Description | Start Time | End Time".
     */
    @Override
    public String toFileString() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + fromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + " | "
                + toDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
