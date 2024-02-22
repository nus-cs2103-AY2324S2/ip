package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task within the Duke application.
 * An event task includes a description, a completion status, and a time range during which the event takes place.
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected LocalDateTime to;

    private final String TASK_TYPE = "E";

    /**
     * Constructs a new Event instance.
     *
     * @param description The description of the event task, detailing what the event is about.
     * @param isDone The initial completion status of the event task. True indicates that the event has been completed, while false indicates that it is still upcoming.
     * @param from The start time of the event in 'dd/MM/yyyy HHmm' format. It is parsed to a LocalDateTime object.
     * @param to The end time of the event in 'dd/MM/yyyy HHmm' format. It is parsed to a LocalDateTime object.
     * @throws DateTimeParseException If either the provided 'from' or 'to' string does not conform to the expected date and time format, indicating an issue with parsing the event's start or end time.
     */
    public Event(String description, Boolean isDone, String from, String to) throws DateTimeParseException {
        super(description, isDone);

        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT));
        } catch (DateTimeParseException e) {
            throw e;
        }

        try {
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Generates and returns a string representation of the event task formatted for file storage.
     * The format includes the task type identifier ('E'), the completion status, the task description, and the event's time range,
     * separated by vertical bars and a dash between the start and end times.
     *
     * @return A string formatted for saving the event task to a file, encapsulating its type, completion status,
     * description, and time range.
     */
    @Override
    public String toFileFormat() {
        return TASK_TYPE + " | " +  this.isDone + " | " + this.description  + " | " + this.from.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT)) + "-" + this.to.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT));
    }

    /**
     * Provides a string representation of the event task for display purposes.
     * This representation includes the task type identifier ('[E]'), the completion status, the task description,
     * and the formatted time range of the event.
     *
     * @return A string representation of the event task, including its type, completion status, description,
     * and time range, suitable for display.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_OUTPUT)) + " to: " + to.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_OUTPUT)) + ")";
    }
}

