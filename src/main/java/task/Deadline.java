package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    public Deadline(String description, LocalDate by) {
        super("[D]", description, by);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return "[D]" + super.toString() + " (By: " + dateformatter.format(this.by) + ")";
    }
}
