package ficin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that spans a specific time period.
 * This class extends {@link Task} and includes start and end times for the event.
 */
public class Event extends Task {
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static final DateTimeFormatter USER_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an Event object with a description, start time, and end time.
     *
     * @param description A description of the event task.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the Event object to a string suitable for file storage.
     *
     * @return A formatted string representing the Event object for file storage, using a predefined date-time format.
     */
    @Override
    public String toFileString() {
        return "E | " + getStatusNumber() + " | " + description + " | "
                + startTime.format(FILE_DATE_TIME_FORMATTER) + " - "
                + endTime.format(FILE_DATE_TIME_FORMATTER);
    }

    /**
     * Provides a user-friendly string representation of the Event object.
     * This includes the type of the task, its status, description, and its start and end times in a readable format.
     *
     * @return A string representing the Event object, formatted for easy reading.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[E]").append(super.toString()).append(" (from: ")
                .append(formatDateTime(startTime)).append(" to: ").append(formatDateTime(endTime)).append(")");

        return builder.toString();
    }

    /**
     * Formats the date and time of the event for display,
     * showing the date and optionally the time if it is not midnight.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A string representation of the date and, if applicable, time of the event.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        String datePart = dateTime.format(USER_DATE_FORMATTER);
        if (dateTime.toLocalTime().getHour() != 0 || dateTime.toLocalTime().getMinute() != 0) {
            return datePart + " at " + dateTime.format(TIME_FORMATTER);
        }
        return datePart;
    }
}
