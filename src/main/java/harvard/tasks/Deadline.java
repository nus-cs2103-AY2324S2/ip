package harvard.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, a task with a specific due date.
 * Extends the Task class.
 */
public class Deadline extends Task {

    /** The due date of the deadline task. */
    protected LocalDate by;

    /**
     * Constructs a Deadline object with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the deadline task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline object with the specified description, due date, and completion status.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the deadline task
     * @param isDone      the completion status of the deadline task
     */
    public Deadline(String description, LocalDate by, Boolean isDone) {
        super(description);
        this.by = by;
        if (isDone) {
            super.mark();
        }
    }

    /**
     * Generates a string representation of the Deadline object suitable for saving to a data file.
     *
     * @return a string representing the Deadline object in a format suitable for saving
     */
    @Override
    public String saveString() {
        return "D," + (super.isDone ? "1," : "0,")
                + super.getDescription()
                + ","
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Generates a string representation of the Deadline object suitable for display to the user.
     *
     * @return a string representing the Deadline object in a format suitable for display
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
