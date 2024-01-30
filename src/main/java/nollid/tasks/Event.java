package nollid.tasks;

import java.time.LocalDateTime;

import nollid.Parser;
import nollid.exceptions.NollidException;

/**
 * Event class represents a task with a specified start and end time.
 * It extends the Task class and includes additional functionality for handling events.
 */
public class Event extends Task {
    public static final String USAGE_HINT = "Usage: event [task description] /from [d/m/yyyy] {hh:mm 24hr format} "
            + "/to [d/m/yyyy] {hh:mm 24hr " + "format}";

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
        return this.from.format(Parser.DATE_OUTPUT_FORMAT) + " " + this.from.format(Parser.TIME_FORMAT);
    }

    public String getToString() {
        return this.to.format(Parser.DATE_OUTPUT_FORMAT) + " " + this.to.format(Parser.TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromString() + " to: " + this.getToString() + ")";
    }
}
