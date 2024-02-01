package task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;

    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );
        String startString = "";
        String endString = "";

        for (DateTimeFormatter formatter : formatters) {
            try {
                startString = this.start.format(formatter);
                endString = this.end.format(formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        return "[E]["+ super.getStatusIcon() +"] " + super.getDescription() + " (from: " + startString + " to: " + endString + ")";
    }
}