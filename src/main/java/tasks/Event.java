package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a start and end time.
 * It extends the Task class and adds functionality to store and format the event details.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event object with the specified event description, start time, and end time.
     *
     * @param event The description of the event task.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Event(String event, LocalDateTime start, LocalDateTime end) {
        super(event);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string containing the task type, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                start.format(DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Event object for saving to file.
     *
     * @return A string containing the task type, description, start time, and end time in a format suitable for file storage.
     */
    @Override
    public String toStringForFile() {
        return "E" + super.toStringForFile() + " | " + this.start + " to " + this.end;
    }
}
