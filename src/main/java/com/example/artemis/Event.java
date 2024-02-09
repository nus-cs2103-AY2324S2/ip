package com.example.artemis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specified start and end time in the Artemis application.
 * Extends the Task class.
 */
public class Event extends Task {
    public static final String INPUT_DATE_FORMAT = "dd-MM-yyyy HHmm";
    public static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy h:mma";
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from         The start time of the event in the format "dd-MM-yyyy HHmm".
     * @param to           The end time of the event in the format "dd-MM-yyyy HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);

        assert description != null : "Description cannot be null";
        assert from != null : "Start time cannot be null";
        assert to != null : "End time cannot be null";
    }

    /**
     * Parses the date-time string and converts it to a LocalDateTime object.
     *
     * @param dateTimeString The date-time string in the format "dd-MM-yyyy HHmm".
     * @return The LocalDateTime representation of the date-time.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + fromDateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + " to: "
                + toDateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")";
    }

    /**
     * Returns a string representation of the Event task for storage in a file.
     *
     * @return A string representation of the Event task for storage in a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + fromDateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + " - "
                + toDateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
    }
}
