package duke.task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * The duke.task.Event class represents a task that occurs within a specified time range.
 */
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an duke.task.Event object with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = parseDateTime(startTime);
        this.endTime = parseDateTime(endTime);
    }

    /**
     * Gets the start time of the task.
     *
     * @return The LocalDateTime representing the start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the task.
     *
     * @return The LocalDateTime representing the end time.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Parses the date and time from the string representation.
     *
     * @param dateTimeString The string representation of date and time.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns a formatted string representation of the event task".
     *
     * @return The formatted string representation of the event task.
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     * @return The formatted string for writing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s",
                isDone ? 1 : 0,
                description,
                startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
