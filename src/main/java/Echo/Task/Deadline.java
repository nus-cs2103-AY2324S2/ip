/*
 * Package: Echo.Task
 * Module/Project Name: Echo
 * File: Deadline.java
 *
 * Description:
 * This class represents a deadline task, a specific type of task with a due date and time.
 * It extends the Task class.
 *
 */

package Echo.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time in string format.
     * @throws IllegalArgumentException if the provided date/time format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);

        try {
            // Try parsing with the first format
            this.by = LocalDateTime.parse(by,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e1) {
            try {
                // Try parsing with another format
                this.by = LocalDateTime.parse(by,
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e2) {
                // Handle the exception or throw it again
                throw new IllegalArgumentException("Invalid date/time format: " + by);
            }
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Gets the task type of the deadline task.
     *
     * @return The task type code for the deadline task ("D").
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task for saving to a file.
     *
     * @return A formatted string representation of the deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s",
                getTaskType(),
                isDone() ? 1 : 0,
                getDescription(),
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
