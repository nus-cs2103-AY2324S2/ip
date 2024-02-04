package ben.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Ben task management application.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified attributes.
     *
     * @param isDone      Indicates if the task is done or not.
     * @param description The description of the deadline task.
     * @param by          The deadline date of the task.
     */
    public Deadline(boolean isDone, String description, LocalDate by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Converts the Deadline task to a string representation for saving to a file.
     *
     * @return A string representing the Deadline task for saving to a file.
     */
    @Override
    public String saveTask() {
        return "D | " + super.saveTask() + " | " + this.by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
