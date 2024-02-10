package yapper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specified deadline.
 * It extends the Task class and includes additional functionality for deadlines.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the given description, completion status, and deadline.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        assert by != null : "Deadline date and time should not be null";
        this.by = by;
    }

    @Override
    public String toString() {
        assert getDescription() != null : "Task description should not be null";
        assert by != null : "Deadline date and time should not be null";
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] "
                + getDescription() + " (by: " + getFormattedDate() + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        assert getDescription() != null : "Task description should not be null";
        return "D | " + (isDone ? "1" : "0") + " | "
                + getDescription() + " | " + by;
    }

    protected String getFormattedDate() {
        assert by != null : "Deadline date and time should not be null";
        return by.format(
                DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }
}
