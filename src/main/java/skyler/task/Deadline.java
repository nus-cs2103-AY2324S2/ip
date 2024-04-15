package skyler.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import skyler.exception.SkylerException;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline task with the specified description, deadline date, and
     * completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date.
     * @param isDone      The completion status of the task.
     * @throws SkylerException If there is an error creating the Deadline task.
     */
    public Deadline(String description, LocalDate by, boolean isDone) throws SkylerException {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Retrieves the deadline date of the Deadline task.
     *
     * @return The deadline date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type,
     * description, and deadline date.
     *
     * @return The formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}