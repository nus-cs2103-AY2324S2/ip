package quacky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected String stringBy;

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
        return "D | " + super.toFileString() + " | " + this.by;
    }
}
