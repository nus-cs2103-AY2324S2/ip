package chipchat.task;

import java.time.LocalDate;
import java.util.List;

import chipchat.action.CommandType;

/**
 * Represents a type of task with a deadline. Subclass of Task.
 */
public class Deadline extends Task {
    private final LocalDate dueBy;

    /**
     * Constructor of Deadline that takes in a date.
     *
     * @param description name/description of the task
     * @param isDone completion status of the task
     * @param dueBy due date of the task
     */
    public Deadline(String description, boolean isDone, LocalDate dueBy, List<String> tags) {
        super(description, isDone, tags);
        this.dueBy = dueBy;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s) %s", super.toString(), this.dueBy, super.printTags());
    }

    /**
     * Returns the data-format string of the task. For Chipchat-specific storage purposes.
     *
     * @return a string representation of the task in a parsable format
     */
    @Override
    public String dataString() {
        return String.format("%s /isDone %s /by %s", CommandType.DEADLINE, super.dataString(), this.dueBy);
    }
}
