package ukecat.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String isDone, String description, LocalDate by) {
        super(isDone, description);
        this.by = by;
    }

    public String getBy() {
        return by.toString();
    }

    @Override
    public String toString() {
        String info = String.format("(by: %s)", by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return String.format("[D]%s%s %s", this.getStatusIcon(), super.toString(), info);
    }
}
