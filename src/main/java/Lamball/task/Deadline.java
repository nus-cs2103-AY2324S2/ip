package Lamball.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }
    @Override
    public String command() {
        return "deadline " + description + " /by " + by;
    }
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
