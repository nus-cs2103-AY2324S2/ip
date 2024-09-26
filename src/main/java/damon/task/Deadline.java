package damon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task by description, status, and due date of task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a new Deadline object
     * with String description parameter and LocalDate by parameter.
     *
     * @param description Description of Deadline.
     * @param by Due date of Deadline in yyyy-mm-dd format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline object with String description parameter,
     * boolean isDone parameter, and LocalDate by parameter.
     *
     * @param description Description of Deadline.
     * @param isDone Status of Deadline.
     * @param by Due date of Deadline in yyyy-mm-dd format.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a sentence containing icon, status, description, and due date of Deadline.
     *
     * @return Sentence representing Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
