package com.example.artemis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime byDateTime;
    private LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.byDateTime = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + " - " +
                        to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }
}
