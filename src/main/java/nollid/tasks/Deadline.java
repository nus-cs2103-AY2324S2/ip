package nollid.tasks;

import java.time.LocalDateTime;

import nollid.Parser;

/**
 * Deadline class represents a task with a specified deadline.
 * It extends the Task class and includes additional functionality for handling deadlines.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object with a description and a deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task represented as LocalDateTime.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String getDeadlineString() {
        return this.deadline.format(Parser.DATE_OUTPUT_FORMAT) + " " + this.deadline.format(Parser.TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")";
    }
}
