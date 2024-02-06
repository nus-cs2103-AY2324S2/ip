package chipchat.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate dueBy;

    public Deadline(String description, boolean isDone, LocalDate dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy);
    }
}
