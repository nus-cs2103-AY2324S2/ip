package paimon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getFormattedBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }
    @Override
    public String createSaveData() {
        return String.format("D | %d | %s | %s\n", (super.isDone() ? 1 : 0), this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedBy() + ")";
    }
}
