package Quacky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a deadline task in the Quacky application. A deadline task is a task that needs
 * to be completed before a specific date.
 */
public class Deadline extends Task{
    protected LocalDate by;
    protected String stringBy;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     *
     * @param description The text description of the deadline task.
     * @param by The date the task needs to be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.stringBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.stringBy + ")";
    }

    @Override
    protected String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by ;
    }
}
