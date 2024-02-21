package nicky.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nicky.NickyException;

/**
 * Represents a deadline task in the Nicky application.
 * It extends the Task class and includes a specific deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the given description and deadline date and time.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time.
     * @throws NickyException If there is an issue with the task creation.
     */
    public Deadline(String description, LocalDateTime by) throws NickyException {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type, description, and deadline.
     *
     * @return The formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}
