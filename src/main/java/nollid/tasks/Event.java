package nollid.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import nollid.exceptions.NollidException;

/**
 * Event class represents a task with a specified start and end time.
 * It extends the Task class and includes additional functionality for handling events.
 */
public class Event extends Task {
    public static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    public static final String USAGE_HINT = "Usage: event [task description] /from [d/m/yyyy] {hh:mm 24hr format} "
            + "/to [d/m/yyyy] {hh:mm 24hr " + "format}";
    protected static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);
    protected static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates new Event with specified description, start, and end time.
     *
     * @throws NollidException if end time is before start time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws NollidException {
        super(description);

        if (from.isAfter(to)) {
            throw new NollidException("Start time and date must be before end time and date.");
        }
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public String getFromString() {
        return this.from.format(DATE_OUTPUT_FORMAT) + " " + this.from.format(TIME_FORMAT);
    }

    public String getToString() {
        return this.to.format(DATE_OUTPUT_FORMAT) + " " + this.to.format(TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromString() + " to: " + this.getToString() + ")";
    }
}
