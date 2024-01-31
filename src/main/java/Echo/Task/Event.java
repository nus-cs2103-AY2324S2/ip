package Echo.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);

        try {
            // Try parsing 'from' with the first format
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e1) {
            try {
                // Try parsing 'from' with another format
                this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e2) {
                // Handle the exception or throw it again
                throw new IllegalArgumentException("Invalid date/time format for 'from': " + from);
            }
        }

        try {
            // Try parsing 'to' with the first format
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e1) {
            try {
                // Try parsing 'to' with another format
                this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e2) {
                // Handle the exception or throw it again
                throw new IllegalArgumentException("Invalid date/time format for 'to': " + to);
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s | %s", getTaskType(), isDone() ? 1 : 0, getDescription(), from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
