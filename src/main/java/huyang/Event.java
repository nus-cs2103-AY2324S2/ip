package huyang;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for the Event class.
     *
     * @param taskName The name of the event task.
     * @param start    The start time of the event.
     * @param end      The end time of the event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        assert start.isBefore(end) : "Start time must be before end time.";
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    private String getTypeIcon() {
        return "[E]";
    }

    /**
     * Converts the event task to a formatted string for saving to a file.
     *
     * @return A string in file format representing the event task.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return "E | " + (isDone ? "1" : "0") + " | " + taskName + " | "
                + start.format(formatter) + " to " + end.format(formatter);
    }

    /**
     * Creates an Event object from a string in file format.
     *
     * @param fileFormat A string in file format representing an event task.
     * @return An Event object created from the file format string.
     * @throws TaskException if the file format is invalid.
     */
    public static Event fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        String description = parts[2];
        String[] times = parts[3].split(" to "); // Split by " to "

        LocalDateTime start = LocalDateTime.parse(times[0], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(times[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Event event = new Event(description, start, end);
        if (parts[1].equals("1")) {
            event.setDone(true);
        }
        return event;
    }

    /**
     * Converts the event task to a formatted string for displaying to the user.
     *
     * @return A string representing the formatted event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return getTypeIcon() + super.getStatusIcon() + " " + taskName
                + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}
