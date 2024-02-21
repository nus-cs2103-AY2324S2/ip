package linus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadlineDate() + ")";
    }

    public LocalDate getBy() {
        return this.by;
    }

    private String formatDeadlineDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}