package atlas.task;

import atlas.exception.InvalidPriorityException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, int priority) throws DateTimeParseException {
        super(description, priority);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.toString()
                + " | " + priority;
    }

    public LocalDateTime getBy() {
        return by;
    }
}
