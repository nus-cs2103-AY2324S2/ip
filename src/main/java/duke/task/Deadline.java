package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline in the Duke application.
 * It extends the Task class and includes additional functionality to handle deadlines.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline for the task.
     * @param isComplete  The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isComplete) {
        super(description);
        this.isComplete = isComplete;
        this.by = by;
    }

    /**
     * Converts the Deadline object to a format suitable for saving to a file.
     *
     * @return The Deadline object formatted as a string for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s",
            isComplete ? 1 : 0,
            description,
            by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string containing the task type, completion status, description, and deadline.
     */
    @Override
    public String toString() {
        return "D | "
            + (isComplete ? 1 : 0)
            + " | " + description
            + " | " + by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

    }
}
