package tasks;

import java.time.LocalDate;

import enums.TaskType;

/**
 * The Deadline class represents a task of type "Deadline" with a description and a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline instance with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Gets the due date of the deadline task.
     *
     * @return The due date of the deadline task.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Converts the deadline task to a formatted string for saving to a file.
     *
     * @return The deadline task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by;
    }

    /**
     * Converts the deadline task to a string representation with task type, completion status, and due date.
     *
     * @return The string representation of the deadline task with task type, completion status, and due date.
     */
    @Override
    public String toString() {
        return type.getSymbol() + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
