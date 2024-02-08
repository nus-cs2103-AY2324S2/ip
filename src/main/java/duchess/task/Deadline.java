package duchess.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duchess.DuchessException;

/**
 * Deadline class represents a task with a specific deadline in the Duchess program.
 * It extends the Task class and provides methods to manipulate Deadline tasks.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the deadline task
     * @param by the deadline of the task in the format "dd-MM-yyyy HHmm"
     * @throws DuchessException if there is an error parsing the deadline string
     */
    public Deadline(String description, String by) throws DuchessException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DuchessException("Oh dear! Your deadline format is wrong, try something like DD-MM-YYYY 1800.");
        }
    }

    /**
     * Constructs a Deadline object with the given description, completion status, and deadline.
     *
     * @param description the description of the deadline task
     * @param isDone true if the task is completed, false otherwise
     * @param by the deadline of the task in the format "MMM dd yyyy hh:mm a"
     * @throws DuchessException if there is an error parsing the deadline string
     */
    public Deadline(String description, boolean isDone, String by) throws DuchessException {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new DuchessException("Oh dear! Your deadline format is wrong, try something like 10-01-2023 1800.");
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return a string representing the Deadline task including its type, completion status, description, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task in file format.
     * @return a string representing the Deadline task including its type, completion status, description,
     * and deadline for file storage
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}
