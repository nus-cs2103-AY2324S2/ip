package chimp.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline object with the specified text, status, and deadline.
     *
     * @param text   the description of the task
     * @param status the status of the task
     * @param by     the deadline of the task
     */
    public Deadline(String text, TaskStatus status, LocalDate by) {
        super(text, status);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
