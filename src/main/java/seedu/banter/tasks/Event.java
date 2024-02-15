package seedu.banter.tasks;

import java.time.LocalDateTime;

import seedu.banter.utilities.DateTime;


/**
 * Represents a task of type Event in a task list.
 * An Event task has a description, a start time and an end time.
 */
public class Event extends Task {
    private static final String EVENT_ICON = "E";
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event object that should be unmarked and in the future.
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) { // default access modifier
        super(description);
        this.from = from;
        this.to = to;
        Assertions.assertDateTimeIsInTheFuture(from);
        Assertions.assertDateTimeIsInTheFuture(to);
        Assertions.assertTaskIsUnmarked(this);
    }

    /**
     * Constructs a new Event object without restrictions on done status and datetime.
     * @param description
     * @param isDone
     * @param from
     * @param to
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) { // default access modifier
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns icon representing an Event task.
     * @return Icon representing an Event task.
     */
    @Override
    public String getTaskType() {
        return EVENT_ICON;
    }

    /**
     * String representation of an Event task.
     * @return String representation of an Event task.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (from: " + DateTime.displayDateTimeToUser(from)
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
}
