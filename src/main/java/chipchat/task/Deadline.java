package chipchat.task;

import chipchat.action.CommandType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a type of task with a deadline. Subclass of Task.
 */
public class Deadline extends Task {
    private LocalDate dueBy;

    /**
     * Constructor of Deadline that takes in a date.
     *
     * @param description name/description of the task
     * @param isDone completion status of the task
     * @param dueBy due date of the task
     */
    public Deadline(String description, boolean isDone, LocalDate dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy);
    }

    /**
     * Returns the data-format string of the task. For Chipchat-specific storage purposes.
     *
     * @return a string representation of the task in a parsable format
     */
    @Override
    public String dataString() {
        return String.format("%s /isDone %s /dueBy %s", CommandType.DEADLINE, super.dataString(), this.dueBy);
    }
}
