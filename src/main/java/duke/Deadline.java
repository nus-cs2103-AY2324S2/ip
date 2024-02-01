package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by; // Deadline date

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type id of the task.
     *
     * @return The type id of the task ("D" for deadline task).
     */
    @Override
    public String typeid() {
        return "D";
    }

    /**
     * Returns the deadline date formatted as a string.
     *
     * @return The deadline date formatted as a string.
     */
    public String timeprint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String bystring = formatter.format(by);
        return ("~" + bystring); // Returns the deadline date formatted as "~yyyy-MM-dd"
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")"; // Returns a string representation of the deadline task
    }
}
