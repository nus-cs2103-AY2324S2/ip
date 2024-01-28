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
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description + " (by: " + getFormattedDate() + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    protected String getFormattedDate(){
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }
}
