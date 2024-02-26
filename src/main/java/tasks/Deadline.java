package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and adds functionality to store and format the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string containing the task type, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Deadline object for saving to file.
     *
     * @return A string containing the task type, description, and deadline in a format suitable for file storage.
     */
    @Override
    public String toStringForFile() {
        return "D" + super.toStringForFile() + " | " + this.deadline;
    }
}
