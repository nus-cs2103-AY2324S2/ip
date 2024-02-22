package seedu.banter.tasks;

import java.time.LocalDateTime;

import seedu.banter.utilities.DateTime;


/**
 * Represents a task of type Event in a task list.
 * An Event task has a description, a start time and an end time.
 */
public class Event extends Task {
    private static final String EVENT_ICON = "E";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a new Event object that should be unmarked and in the future.
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        Assertions.assertDateTimeIsInTheFuture(from);
        Assertions.assertDateTimeIsInTheFuture(to);
        Assertions.assertTaskIsUnmarked(this);
    }

    /**
     * Constructs a new Event object without restrictions on done status and datetime.
     * @param description The description of the Event task.
     * @param isDone The done status of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns icon representing an Event task.
     * @return Icon representing an Event task.
     */
    @Override
    public String getTaskTypeIcon() {
        return EVENT_ICON;
    }

    /**
     * String representation of an Event task.
     * @return String representation of an Event task.
     */
    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + super.toString() + " (from: " + DateTime.displayDateTimeToUser(from)
                + " to: " + DateTime.displayDateTimeToUser(to) + ")";
    }

    /**
     * Returns the start time of an Event task.
     * @return Start time of an Event task.
     */
    public LocalDateTime getStartDateTime() {
        return from;
    }

    /**
     * Returns the end time of an Event task.
     * @return End time of an Event task.
     */
    public LocalDateTime getEndDateTime() {
        return to;
    }

    /**
     * Returns the start time of an Event task as the sorting priority.
     * @return The start time of an Event task as the sorting priority.
     */
    public LocalDateTime getDateTimePriority() {
        return from;
    }
}
