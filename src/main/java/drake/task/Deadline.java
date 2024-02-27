package drake.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 * Inherits from the {@code Task} class and includes additional information
 * for the deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a {@code Deadline} task with the specified description and due date/time.
     *
     * @param description The description of the deadline.
     * @param by          The LocalDateTime by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    /**
     * Creates a string representation of an instance of this class.
     *
     * @return The String representation of an instance of this class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
