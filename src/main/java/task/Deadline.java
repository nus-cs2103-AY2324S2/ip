package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super("[D]", description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformatter
                = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return "[D]" + super.toString() + " (by: " + dateformatter.format(this.by) + ")";
    }
}
