package ukecat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the UkeCat application.
 * Inherits from the Task class and includes additional methods specific to Deadline tasks.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the specified status, description, and deadline.
     *
     * @param status      The status of the Deadline task.
     * @param description The description of the Deadline task.
     * @param by          The deadline date of the task.
     */
    public Deadline(TaskStatus status, String description, LocalDate by) {
        super(status, description);
        this.by = by;
    }

    /**
     * Gets the string representation of the deadline date.
     *
     * @return The deadline date as a string.
     */
    public String getBy() {
        return by.toString();
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task type, status icon, description, and deadline information.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        String info = String.format("(by: %s)", by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return String.format("[D]%s %s %s", this.getStatusIcon(), super.toString(), info);
    }
}
