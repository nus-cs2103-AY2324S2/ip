package jayne.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jayne.JayneException;

/**
 * Represents an event task. In addition to the basic task properties,
 * an event contains a start and end time.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;
    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param start the start time of the event.
     * @param end the end time of the event.
     */
    public Event(String description, String start, String end) throws JayneException {
        super(description);
        assert start != null && !start.trim().isEmpty() : "Start time should not be null or empty";
        assert end != null && !end.trim().isEmpty() : "End time should not be null or empty";
        this.start = parseDate(start);
        this.end = parseDate(end);
    }
    /**
     * Parses the date string to LocalDate.
     * If the date string is not in a valid format, it throws a JayneException.
     *
     * @param dateString the date string to be parsed.
     * @return the parsed LocalDate.
     * @throws JayneException if the date string is not in a valid format.
     */
    private LocalDate parseDate(String dateString) throws JayneException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new JayneException("Unable to parse the date: " + dateString);
        }
    }
    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
    /**
     * Returns the string representation of the event task,
     * including its type, status, description, start time, and end time.
     *
     * @return a string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
    /**
     * Returns the string representation of the event task in a format suitable for file storage.
     * The format includes the task type, status, description, start time, and end time.
     *
     * @return a string representation of the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return "E | " + super.toFileFormat() + " | " + start.format(formatter) + "-" + end.format(formatter);
    }

}
