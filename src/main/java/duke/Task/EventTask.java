package duke.Task;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a start time and an end time.
 * Inherits from the Task class.
 */
public class EventTask extends Task {
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructs an EventTask object with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event task.
     * @param endTime The end time of the event task.
     */
    public EventTask(String description, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the event task to a string representation for saving to a file.
     *
     * @return The string representation of the event task for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime.format(DateTimeFormatter.ofPattern("HHmm")) + " | " + endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Converts the event task to a string representation for displaying to the user.
     *
     * @return The string representation of the event task for displaying to the user.
     */
    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to: " + endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}