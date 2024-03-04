package yapper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that spans a specific time range.
 * It extends the Task class and includes additional functionality for events.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the given description, completion status, start time, and end time.
     *
     * @param description The description of the event.
     * @param isDone      The completion status of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        assert from != null : "Event start date and time should not be null";
        assert to != null : "Event end date and time should not be null";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        assert getDescription() != null : "Task description should not be null";
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] "
                + getDescription() + " " + getFormattedDateRange();
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        assert getDescription() != null : "Task description should not be null";
        return "E | " + (isDone ? "1" : "0") + " | "
                + getDescription() + " | " + from + " | " + to;
    }

    protected String getFormattedDateRange() {
        assert from != null : "Event start date and time should not be null";
        assert to != null : "Event end date and time should not be null";
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"))
                + " to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }
}
