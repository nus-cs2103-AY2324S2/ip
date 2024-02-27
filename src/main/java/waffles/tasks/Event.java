package waffles.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specific start and end time in the waffles chatbot application.
 * It extends the Task class.
 */
public class Event extends Task {

    private static final String EVENT_MESSAGE = "[E]%s (from: %s to: %s)";
    private static final String EVENT_FILE_TEMPLATE = "E | %s | %s | %s";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("EEE HHmm dd/MM/yyyy");

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event in the format "yyyy/MM/dd HHmm".
     * @param endTime The end time of the event in the format "yyyy/MM/dd HHmm".
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter acceptFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        this.startTime = LocalDateTime.parse(startTime, acceptFormatter);
        this.endTime = LocalDateTime.parse(endTime, acceptFormatter);
    }

    /**
     * Returns the string representation of the event task for saving to a file.
     *
     * @return The string representation of the event task for saving to a file.
     */
    public String toTaskFileTemplate() {
        return String.format(EVENT_FILE_TEMPLATE, super.toTaskFileTemplate(), startTime, endTime);
    }

    /**
     * Returns the string representation of the event task for saving to a file.
     *
     * @return The string representation of the event task for saving to a file.
     */
    @Override
    public String toString() {
        return String.format(EVENT_MESSAGE, super.toString(),
                startTime.format(returnFormatter), endTime.format(returnFormatter));
    }
}
