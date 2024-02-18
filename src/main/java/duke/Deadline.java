package duke;
import java.time.LocalDate;
/**
 * Represents a task with a deadline in the task list.
 * Subclass of the Task class.
 */
public class Deadline extends Task {
    /**
     * The deadline date for this task.
     */
    protected LocalDate by;

    /**
     * Constructs a Deadline object with the given description and deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description, Task.TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    /**
     * Returns the type of the task (deadline).
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }
    /**
     * Returns a formatted string for writing the Deadline object to a file.
     *
     * @return A formatted string for writing the Deadline object to a file.
     */
    @Override
    public String toFileString() {
        return String.format("%s |  %d | %s | %s", getType(), isDone ? 1 : 0 , description, by);
    }
    @Override
    public LocalDate getDate() {
        return by;
    }
}
