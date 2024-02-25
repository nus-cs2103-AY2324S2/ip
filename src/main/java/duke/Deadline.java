package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a deadline task in the Duke application.
 * A deadline task is a task with a deadline
 *
 * @author Qin Boan
 */
public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Creates a new Deadline instance.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @throws DukeException If the provided deadline is in an invalid format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            // Handle exception or set a default value if the date is in the wrong format
            throw new DukeException("Invalid date format (yyyy-mm-dd).");
        }
    }

    /**
     * Returns a formatted string of the deadline for display.
     *
     * @return A formatted string representing the deadline.
     */
    public String getBy() {
        return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    /**
     * Returns a formatted string of the deadline for file storage
     *
     * @return A formatted string representing the deadline.
     */
    public String getByForFile() {
        return byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a formatted string for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}

