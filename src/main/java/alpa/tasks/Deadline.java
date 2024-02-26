package alpa.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object with the given description and deadline.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        assert description != null : "Description cannot be empty";
        assert deadline != null : "Deadline cannot be empty";
        this.deadline = deadline;
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     * @return The task in file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[ D ]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
