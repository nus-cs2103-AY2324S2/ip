package adam.task;

import adam.AdamException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @inheritDoc
 * A deadline additionally has a date for the deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Returns a deadline task.
     *
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     * @throws AdamException If the date provided is in the wrong format.
     */
    public Deadline(String description, String by) throws AdamException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new AdamException("Invalid date or date format");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toFileString() {
        return "D," + super.toFileString() + "," + by;
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
