package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with specific start and end times.
 * Extends the Task class.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final String PATTERN = "dd MMM yyyy hh:mm a";

    /**
     * Instantiates a new Event.
     *
     * @param description The description of the event task.
     * @param isDone      Indicates whether the event task has been completed.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string containing the task type, description, start time and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (start time: " + this.startTime.format(DateTimeFormatter.ofPattern(PATTERN))
                + ")"
                + " (end time: " + this.endTime.format(DateTimeFormatter.ofPattern(PATTERN))
                + ")";
    }

    /**
     * Returns a string representation of the Event object to save to file.
     *
     * @return A string containing the task type, description, start time and end time formatted for saving to file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E|"
                + super.toFileString()
                + "|"
                + this.startTime.format(fileFormatter)
                + " to "
                + this.endTime.format(fileFormatter);
    }
}
