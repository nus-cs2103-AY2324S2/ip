package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    LocalDateTime by;
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );
        String byString = "";
        for (DateTimeFormatter formatter : formatters) {
            try {
                byString = this.by.format(formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        return "[D]["+ super.getStatusIcon() +"] " + super.getDescription() + " (by: " + byString + ")";
    }
}