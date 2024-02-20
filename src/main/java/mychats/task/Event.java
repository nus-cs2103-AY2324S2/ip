package mychats.task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task that occurs within a specified date and time range.
 */
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = parseDateTime(startTime);
        this.endTime = parseDateTime(endTime);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Parses the date and time from the given date and time string of format "yyyy-MM-dd HHmm".
     *
     * @param dateTimeString String representation of date and time in format "yyyy-MM-dd HHmm".
     * @return Parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns a formatted string representation of the event task".
     *
     * @return Formatted string representation of the event task.
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    /**
     * Converts the event task into a string format suitable for writing to a file.
     *
     * @return Formatted string for writing to a file.
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
