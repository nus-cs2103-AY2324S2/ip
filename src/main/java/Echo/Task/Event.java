/*
 * Package: Echo.Task
 * Module/Project Name: Echo
 * File: Event.java
 *
 * Description:
 * This class represents an event task, a specific type of task with start and end date and time.
 * It extends the Task class.
 */

package Echo.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event task.
     * @param from        The start date and time of the event in string format.
     * @param to          The end date and time of the event in string format.
     * @throws IllegalArgumentException if the provided date/time format for 'from' or 'to' is invalid.
     */
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

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }

    /**
     * Gets the task type of the event task.
     *
     * @return The task type code for the event task ("E").
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the event task for saving to a file.
     *
     * @return A formatted string representation of the event task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s | %s",
                getTaskType(),
                isDone() ? 1 : 0,
                getDescription(),
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
