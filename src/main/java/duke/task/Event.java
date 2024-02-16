package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.JamieException;

/**
 * Represents an event task with a specified start and end time.
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time in the format "dd/MM/yyyy HHmm".
     * @param to          The end time in the format "dd/MM/yyyy HHmm".
     * @throws JamieException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws JamieException {
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
     * @throws JamieException If the date format is invalid.
     */
    public Event(String description, String from, String to, boolean isDone) throws JamieException {
        super(description, isDone);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    /**
     * Parses the date and time from the given string.
     *
     * @param dateTime The date and time in the format "dd/MM/yyyy HHmm".
     * @return The parsed LocalDateTime.
     * @throws JamieException If the date format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) throws JamieException {
        try {
            return LocalDateTime.parse(dateTime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JamieException("Invalid date format for event. Please use 'dd/MM/yyyy HHmm'.");
        }
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getFrom() {
        return this.fromDateTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
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
                + " (from: " + FORMATTER.format(this.fromDateTime)
                + " to: " + FORMATTER.format(this.toDateTime) + ")";
    }

    /**
     * Returns a string representation of the Event task for saving to a file.
     *
     * @return A string in the format "E | Status | Description | Start Time | End Time".
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + FORMATTER.format(this.fromDateTime) + " | "
                + FORMATTER.format(this.toDateTime);
    }
}
