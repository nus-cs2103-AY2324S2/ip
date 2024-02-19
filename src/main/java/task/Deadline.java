package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates DEADLINE task. Inherits from task.Task.
 *
 * @author Tan Qin Yong
 */
public class Deadline extends Task {
    /** The deadline associated with the task. */
    private LocalDate by;

    /**
     * Constructor for deadline object.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline associated with the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Gets the type of the task.
     *
     * @return The string "deadline" representing the type of the task.
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Returns a string representation of the task.Deadline object.
     *
     * @return A string representation of the task.Deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedBy = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
