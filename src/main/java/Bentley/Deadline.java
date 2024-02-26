package bentley;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline date.
 * It extends the Task class and includes functionality to handle deadlines.
 */
public class Deadline extends Task {
    /**
     * The deadline date for the task.
     */
    private LocalDate deadlineDate;

    /**
     * Constructs a Deadline object with the given description and deadline date.
     *
     * @param description  The description of the task.
     * @param deadlineDate The deadline date of the task.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(" (D) " + description + " | " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns a string representation of the Deadline object.
     * Overrides the toString method in the Task class.
     *
     * @return A formatted string containing the task description and deadline date.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
