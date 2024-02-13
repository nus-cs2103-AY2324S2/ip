package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an task with start and end date-time.
 * Extends the abstract base class Task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Initializes a new Event task with the given description, start, and end date-time.
     *
     * @param description The description of the event task.
     * @param start       The start date and time of the event.
     * @param end         The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task,
     * including its status icon, description, start, and end date-time.
     *
     * @return A string representing the Event task with its start and end date-time.
     */
    @Override
    public String toString() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedDateTime = this.start.format(customFormatter);
        String formattedDateTime1 = this.end.format(customFormatter);
        return "[E]" + super.toString() + " (from: " + formattedDateTime + " to: " + formattedDateTime1 + ")";
    }

    /**
     * Returns a string representation of the Event task for saving,
     * including task type, completion status, description, start, and end date-time.
     *
     * @return A string representing the Event task for saving in text file.
     */
    @Override
    public String toSave() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDateTime = this.start.format(customFormatter);
        String formattedDateTime1 = this.end.format(customFormatter);
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + formattedDateTime + " | " + formattedDateTime1;
    }
}
