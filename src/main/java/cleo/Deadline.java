package cleo;

import java.time.LocalDateTime;;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a description and a deadline date/time.
 */
public class Deadline extends Task {
    /**
     * The deadline date and time of the task.
     */
    private LocalDateTime by;
    /**
     * Creates a new Deadline task.
     *
     * @param description The textual description of the Deadline task.
     * @param by Deadline date and time in the format "d/M/yyyy HHmm"
     * @throws DukeException if the provided deadline format is invalid
     */
    Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use 'd/M/yyyy HHmm', e.g., '2/12/2019 1800'.");
        }
    }

    /**
     * Returns a string representationrentation to visually identify a Deadline task.
     *
     * @return The string "[D]"
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }
    /**
     * Returns the deadline date and time of the task.
     *
     * @return The deadline (LocalDateTime)
     */

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a formatted string representation of the Deadline, including description and deadline.
     *
     * @return The formatted deadline string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return super.toString() + " (by: " + this.getBy().format(formatter) + ")";
    }
}
