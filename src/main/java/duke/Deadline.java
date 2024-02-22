package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline in the Duke application.
 * A deadline task includes a description, a completion status, and a deadline by which the task should be completed.
 */
public class Deadline extends Task {

    /**
     * The deadline by which the task needs to be completed.
     */
    protected LocalDateTime by;

    private final String TASK_TYPE = "D";

    /**
     * Constructs a new Deadline instance.
     *
     * @param description The description of the deadline task, providing details about the task.
     * @param isDone The initial completion status of the task. True if the task has already been completed, false otherwise.
     * @param by The deadline for the task in 'dd/MM/yyyy HHmm' format. It is parsed to a LocalDateTime object.
     * @throws DateTimeParseException If the provided 'by' string does not conform to the expected date and time format.
     */
    public Deadline(String description, Boolean isDone, String by) throws DateTimeParseException {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT));
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse the date/time: " + by);
            throw e;
        }
    }

    /**
     * Generates and returns a string representation of the deadline task formatted for file storage.
     * The format includes the task type identifier ('D'), the completion status, the task description, and the deadline,
     * separated by vertical bars.
     *
     * @return A string formatted for saving the deadline task to a file, encapsulating its type, completion status,
     * description, and deadline.
     */
    @Override
    public String toFileFormat() {
        return TASK_TYPE + " | " +  this.isDone + " | " + this.description  + " | " + by.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT));
    }

    /**
     * Provides a string representation of the deadline task for display purposes.
     * This representation includes the task type identifier ('[D]'), the completion status, the task description,
     * and the formatted deadline.
     *
     * @return A string representation of the deadline task, including its type, completion status, description,
     * and deadline, suitable for display.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_OUTPUT)) + ")";
    }
}

