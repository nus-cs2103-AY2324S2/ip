import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Deadline task
 * It extends the Task class, adding a due date to the task
 */
public class Deadline extends Task {
    // protected String dueDate;
    protected LocalDateTime dueDateTime;

    /**
     * Constructs a new Deadline task with a specified description and due date
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            this.dueDateTime = LocalDateTime.parse(dueDate, formatter1);
        } catch (DateTimeParseException e) {
            this.dueDateTime = LocalDateTime.parse(dueDate, formatter2);
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task type (D for Deadline), the task description, and
     * the due date.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "D" + super.toString() + " | by: " + this.dueDateTime.format(formatter);
    }
}