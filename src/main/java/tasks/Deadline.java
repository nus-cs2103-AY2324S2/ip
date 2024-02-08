package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that has an ending date
 */
public class Deadline extends Task {

    protected LocalDate dueBy;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.dueBy = by;
    }

    public Deadline(String description, LocalDate by, String status) {
        super(description, status);
        this.dueBy = by;
    }

    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " /by " + dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
