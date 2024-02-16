package ken.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 *
 * It extends the Task class and includes additional information about the deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline date and time.
     */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null;
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the deadline task for file storage.
     *
     * @return A string representation of the deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

}
