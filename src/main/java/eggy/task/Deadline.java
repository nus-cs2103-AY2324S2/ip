package eggy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by;
    }
}
