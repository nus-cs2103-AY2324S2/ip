package skyler.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import skyler.exception.SkylerException;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by, boolean isDone) throws SkylerException {
        super(description, isDone);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}