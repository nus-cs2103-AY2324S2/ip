package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate due_by;

    public Deadline(String description, String due_by) {
        super(description);
        this.due_by = parseDate(due_by);
    }

    private static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(date, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due_by + ")";
    }

    @Override
    public String dataString() {
        return String.format("deadline|%s|%s", super.dataString(), this.due_by);
    }
}
