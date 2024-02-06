package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String due;

    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.due + ")";
    }
}
