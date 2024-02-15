package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.JamieException;

/**
 * Represents a task with a deadline, which is a subclass of Task.
 */
public class Deadline extends Task {

    protected LocalDateTime byDateTime;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline in the format "dd/MM/yyyy HHmm".
     * @throws JamieException If the date format is invalid.
     */
    public Deadline(String description, String by) throws JamieException {
        super(description);
        this.byDateTime = parseDateTime(by);
    }

    /**
     * Constructs a Deadline task with the specified description, deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by          The deadline in the format "dd/MM/yyyy HHmm".
     * @param isDone      True if the task is completed, false otherwise.
     * @throws JamieException If the date format is invalid.
     */
    public Deadline(String description, String by, boolean isDone) throws JamieException {
        super(description, isDone);
        this.byDateTime = parseDateTime(by);
    }

    /**
     * Parses the deadline date and time from the given string.
     *
     * @param by The deadline in the format "dd/MM/yyyy HHmm".
     * @return The parsed LocalDateTime.
     * @throws JamieException If the date format is invalid.
     */
    private LocalDateTime parseDateTime(String by) throws JamieException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new JamieException("Invalid date format. Please use 'dd/MM/yyyy HHmm'.");
        }
    }

    /**
     * Returns the deadline date and time.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getBy() {
        return this.byDateTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string in the format "[D][Status] Description (by: Deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for saving to a file.
     *
     * @return A string in the format "D | Status | Description | Deadline".
     */
    @Override
    public String toFileString() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}