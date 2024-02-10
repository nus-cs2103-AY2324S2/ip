package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadlines class represents a task with a specific deadline in the Roland task management application.
 * It extends the Task class and includes additional information about the deadline.
 *
 * @author wolffe88
 */

public class Deadlines extends Task {

    protected LocalDate by;

    /**
     * Constructs a new Deadlines task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline date of the task.
     */
    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString method to provide a formatted representation of the deadline task.
     *
     * @return The formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
