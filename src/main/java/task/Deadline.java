package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * <p>
 * This class extends the {@link Task} class and is used to represent tasks that
 * have a deadline. It provides a constructor to create a new deadline task with
 * a description and a deadline date.
 * </p>
 */
public class Deadline extends Task {

    protected LocalDate deadlineDate;

    /**
     * Constructs a new {@code Deadline} instance with the specified description and
     * deadline date.
     * 
     * @param description The description of the deadline task.
     * @param by          The deadline date of the deadline task.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns the deadline date of the deadline task.
     * 
     * @return The deadline date of the deadline task.
     */
    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Returs the string representation of the deadline task.
     * 
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        String deadlineDateString = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.getPriorityString() + "[D]" + super.toString() + " (by: " + deadlineDateString + ")";
    }

    /**
     * Returns the string representation of the deadline task to be saved in the
     * hard disk.
     * 
     * @return The string representation of the deadline task to be saved in the
     *         hard disk.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + deadlineDate;
    }
}
