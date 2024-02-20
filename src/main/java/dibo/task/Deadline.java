package dibo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a deadline task of the user.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new Deadline object with the specified parameters.
     *
     * @param description The string description of the task.
     * @param by The LocalDate object of date the task needs to be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String outputByString = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + outputByString + ")";
    }

    @Override
    public String getSaveFormat() {
        String outputByString = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "deadline | " + super.getSaveFormat() + " | " + outputByString;
    }
}
