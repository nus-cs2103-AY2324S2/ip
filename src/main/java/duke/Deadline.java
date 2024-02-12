package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.generateByDateString() + ")";
    }

    @Override
    public String toTaskSaveString() {
        return "D|" + this.getStatusInt() + "|" + this.description + "|" + this.generateBySaveString();
    }

    private String generateByDateString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String generateBySaveString() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
