package zack.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zack.ZackException;


/**
 * Represents a task with a specific event duration.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from        The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param to          The end time of the event in "yyyy-MM-dd HHmm" format.
     * @param isDone      True if the event is marked as done, false otherwise.
     * @throws ZackException If there is an error in parsing the event times or if the format is invalid.
     */
    public Event(String description, String from, String to, boolean isDone, LocalDateTime addedTime)
            throws ZackException {
        super(description, isDone, addedTime);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new ZackException("Invalid date and time format. Please enter in yyyy-MM-dd HHmm format.");
        }
    }

    /**
     * Checks if the event is happening on the specified date and time.
     *
     * @param date The date and time to check.
     * @return True if the event is happening at the specified date and time, false otherwise.
     */
    public boolean isHappeningOnDate(LocalDateTime date) {
        return !from.toLocalDate().isAfter(date.toLocalDate()) && !to.toLocalDate().isBefore(date.toLocalDate());
    }

    /**
     * Returns a string in a specific format for saving to a file.
     *
     * @return A string representation of the Event task for saving to a file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + formattedFrom
                + " to " + formattedTo + " | " + addedTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
