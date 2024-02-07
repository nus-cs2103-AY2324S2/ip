package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedDateTime = this.by.format(customFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }

    @Override
    public String toSave() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDateTime = this.by.format(customFormatter);
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + formattedDateTime;
    }
}
