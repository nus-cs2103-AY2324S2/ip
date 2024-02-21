package duke.task;
import java.time.LocalDate;

/**
 * Represents a task with a deadline. It extends the {@code Task} class and includes a specific due date.
 */
public class Deadline extends Task{
    protected LocalDate by;
    
    /**
     * Constructs a {@code Deadline} task with the specified description and due date.
     *
     * @param description The task's textual description.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    
    /**
     * Returns the due date of the task.
     *
     * @return The due date as a {@code LocalDate}.
     */
    public LocalDate getBy() {
        return this.by;
    }
    
    /**
     * Returns a string representation of the deadline task, including its type, status, description, and due date.
     *
     * @return A formatted string indicating the task's type (D for Deadline), completion status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateForPrinting(this.getBy()) + ")";
    }
}
