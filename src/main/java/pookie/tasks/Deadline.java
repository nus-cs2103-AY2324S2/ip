package pookie.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    /**
     * The deadline of task.
     */
    protected String by;

    /**
     * Constructor for deadline task.
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        LocalDateTime d = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String printd = d.format(f);
        return "[D]" + super.toString() + " (by: " + printd + ")";
    }

    /**
     * Returns the string representation of the deadline task to be written to file.
     * @return The string representation of the deadline task to be written to file.
     */
    @Override
    public String writeToFileString() {
        return "deadline" + this.description + " /by " + this.by;
    }

}
