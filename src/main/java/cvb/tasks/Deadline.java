package cvb.tasks;

import java.time.LocalDate;

import cvb.utils.DateTime;

/**
 * Represents a task with a specific deadline.
 * Extends the {@code Task} class and includes functionality to handle deadlines.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a new {@code Deadline} instance with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    /**
     * Constructs a new {@code Deadline} instance with the specified description, completion status, and deadline.
     *
     * @param description the description of the task
     * @param isDone      the completion status of the task
     * @param by          the deadline of the task
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Converts the {@code Deadline} object to a string format suitable for saving to a file.
     *
     * @return a formatted string representing the task for file storage
     */
    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + DateTime.dateToFile(this.by);
    }

    /**
     * Returns a string representation of the {@code Deadline} object.
     *
     * @return a formatted string representing the task for display
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.dateToString(this.by) + ")";
    }
}
