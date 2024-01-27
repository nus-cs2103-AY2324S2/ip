package com.example.artemis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"))
                + " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + " - "
                + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }
}
