package ada.task;

import ada.AdaException;

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
     * @throws AdaException If the date provided is in the wrong format.
     */
    public Deadline(String description, String by) throws AdaException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new AdaException("Invalid date or date format");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toFileString() {
        return "D," + super.toFileString() + "," + this.by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
