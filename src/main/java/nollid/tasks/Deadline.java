package nollid.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import nollid.Parser;

/**
 * Deadline class represents a task with a specified deadline.
 * It extends the Task class and includes additional functionality for handling deadlines.
 */
public class Deadline extends Task {
    /**
     * The deadline of the task represented as LocalDateTime.
     */
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

    /**
     * Constructs a Deadline object with a description and a deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task represented as LocalDateTime.
     * @param tags        The List of tags associated with the Task.
     */
    public Deadline(String description, LocalDateTime deadline, ArrayList<String> tags) {
        super(description, tags);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Gets the formatted deadline string.
     *
     * @return The deadline formatted as a string using DATE_OUTPUT_FORMAT and TIME_FORMAT from Parser.
     */
    public String getDeadlineString() {
        return this.deadline.format(Parser.DATE_OUTPUT_FORMAT) + " " + this.deadline.format(Parser.TIME_FORMAT);
    }

    /**
     * Overrides the toString method to provide a string representation of the Deadline object.
     *
     * @return A formatted string representing the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")" + super.getTagsString();
    }
}
