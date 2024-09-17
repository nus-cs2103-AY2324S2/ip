package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.JamieException;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime byDateTime;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline in the format "dd/MM/yyyy HHmm".
     * @throws JamieException If the date format is invalid.
     */
    public Deadline(String description, String by) throws JamieException {
        super(description);
        this.byDateTime = parseDateTime(by); // Using 'this' for consistency
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
        this.byDateTime = parseDateTime(by); // Ensuring the date is parsed consistently
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
            return LocalDateTime.parse(by, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JamieException("Invalid date format for deadline. Please use 'dd/MM/yyyy HHmm'.");
        }
    }

    /**
     * Returns the deadline date and time.
     *
     * @return The deadline date and time as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return this.byDateTime; // Consistency in using 'this' for class fields
    }

    public void setBy(String by) throws JamieException {
        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new JamieException("Invalid date format for deadline. Please use 'dd/MM/yyyy HHmm'.");
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string in the format "[D][Status] Description (by: Deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + FORMATTER.format(this.byDateTime) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for saving to a file.
     *
     * @return A string in the format "D | Status | Description | Deadline".
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + FORMATTER.format(this.byDateTime);
    }
}
