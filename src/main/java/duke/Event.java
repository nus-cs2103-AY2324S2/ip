package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents an event task in the Duke application.
 * An event is a task that starts at a specific time and ends at a specific time.
 *
 * @author Qin Boan
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event in the format "yyyy-MM-dd HH:mm".
     * @param end The end time of the event in the format "yyyy-MM-dd HH:mm".
     * @throws DukeException If the provided start or end time is in an invalid format.
     */
    public Event(String description, String start, String end) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.start = LocalDateTime.parse(start, formatter);
            this.end = LocalDateTime.parse(end, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid datetime format (yyyy-MM-dd HH:mm).");
        }
    }

    /**
     * Returns the formatted start time of the event.
     *
     * @return A string representing the start time formatted as "MMM d yyyy HH:mm".
     */
    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Returns the formatted end time of the event.
     *
     * @return A string representing the end time formatted as "MMM d yyyy HH:mm".
     */
    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Returns the start time of the event formatted for file storage.
     *
     * @return A string representing the start time formatted as "yyyy-MM-dd HH:mm".
     */
    public String getStartForFile() {
        return start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the end time of the event formatted for file storage.
     *
     * @return A string representing the end time formatted as "yyyy-MM-dd HH:mm".
     */
    public String getEndForFile() {
        return end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the event task, including its type, description,
     * and start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }
}
