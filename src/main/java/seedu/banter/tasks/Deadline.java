package seedu.banter.tasks;

import java.time.LocalDateTime;

import seedu.banter.utilities.DateTime;


/**
 * Represents a task of type Deadline in a task list.
 * A Deadline task has a description and a due date.
 */
public class Deadline extends Task {
    private static final String DEADLINE_ICON = "D";
    private final LocalDateTime by;

    /**
     * Constructs a new Deadline object that should be unmarked and in the future.
     * @param description The description of the Deadline task.
     * @param by The due date of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        Assertions.assertDateTimeIsInTheFuture(by);
        Assertions.assertTaskIsUnmarked(this);
    }

    /**
     * Constructs a new Deadline object without restrictions on done status and datetime.
     * @param description The description of the Deadline task.
     * @param isDone The done status of the Deadline task.
     * @param by The due date of the Deadline task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns icon representing a Deadline task.
     * @return Icon representing a Deadline task.
     */
    @Override
    public String getTaskType() {
        return DEADLINE_ICON;
    }

    /**
     * String representation of a Deadline task.
     * @return String representation of a Deadline task.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + DateTime.displayDateTimeToUser(by) + ")";
    }

    /**
     * Returns the due date of a Deadline task.
     * @return Due date of a Deadline task.
     */
    public LocalDateTime getDueDateTime() {
        return by;
    }

    /**
     * Returns the due date of a Deadline task as the sorting priority.
     * @return The due date of a Deadline task as the sorting priority.
     */
    public LocalDateTime getDateTimePriority() {
        return by;
    }
}
