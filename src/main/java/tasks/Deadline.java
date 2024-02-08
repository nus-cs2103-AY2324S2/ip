package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that has an ending date
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, String status) {
        super(description, status);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " /by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
