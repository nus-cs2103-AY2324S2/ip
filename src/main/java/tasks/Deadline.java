package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a deadline task.
 * A deadline task is a task that needs to be done before a specific date.
 */
public class Deadline extends Task {

    protected LocalDate by; // The deadline date

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date in the format of a String that can be parsed as a LocalDate.
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the deadline task, including its type,
     * status, description, and deadline date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.getMonth() + " " + by.getDayOfMonth() + " " + by.getYear() + ")";
    }

    /**
     * Returns a string formatted for file storage, including the task type,
     * completion status, description, and deadline date.
     *
     * @return A string suitable for storing the task in a file.
     */
    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "D | " + completed + " | " + this.description + " | " + this.by;
    }
}
