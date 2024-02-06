package Martin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate due;
    private final String DATE_TIME_FORMAT = "MMM d yyyy";

    Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
                + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.due;
    }
}
