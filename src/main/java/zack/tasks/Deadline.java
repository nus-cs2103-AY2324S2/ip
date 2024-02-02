package zack.tasks;

import zack.ZackException;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description, deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task in "yyyy-MM-dd HHmm" format.
     * @param isDone      True if the task is marked as done, false otherwise.
     * @throws ZackException If there is an error in parsing the deadline or if the format is invalid.
     */
    public Deadline(String description, String by, boolean isDone) throws ZackException {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new ZackException("Invalid date and time format. Please enter in yyyy-MM-dd HHmm format.");
        }
    }

    /**
     * Checks if the deadline of the task falls on the given date.
     *
     * @param date The date to check.
     * @return True if the deadline is on the specified date, false otherwise.
     */
    public boolean isOnDate(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

    /**
     * Returns a string in a specific format for saving to a file.
     *
     * @return A string representation of the Deadline task for saving to a file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedBy = by.format(formatter);
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + formattedBy;
    }
}
