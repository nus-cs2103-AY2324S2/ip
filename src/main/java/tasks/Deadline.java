package tasks;

import java.time.LocalDate;

import utils.DateTime;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + DateTime.dateToFile(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.dateToString(this.by) + ")";
    }
}
