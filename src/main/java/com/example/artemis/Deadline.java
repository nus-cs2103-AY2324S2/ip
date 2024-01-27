package com.example.artemis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Artemis application.
 * Extends the Task class.
 */
public class Deadline extends Task {
    private LocalDateTime byDateTime;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by           The deadline of the task in the format "dd-MM-yyyy HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDateTime = parseDateTime(by);
    }

    /**
     * Parses the deadline string and converts it to a LocalDateTime object.
     *
     * @param dateTimeString The deadline string in the format "dd-MM-yyyy HHmm".
     * @return The LocalDateTime representation of the deadline.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for storage in a file.
     *
     * @return A string representation of the Deadline task for storage in a file.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }
}


