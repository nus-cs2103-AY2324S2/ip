package tony.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a due date.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Creates a new Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.type = TaskType.DEADLINE;
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Deadline task for display purposes.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + ")";
    }

    /**
     * Returns a formatted string representation of the Deadline task for storage purposes.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String formattedString() {

        return "D" + super.formattedString() + "|" + dueDate.toString();
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return The type of the task ("DEADLINE").
     */
    @Override
    public String getType() {
        return type.toString();
    }

    public void setDueDate(LocalDateTime date) {
        this.dueDate = date;
    }
}
