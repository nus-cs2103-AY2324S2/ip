package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Extends the abstract base class Task.
 */
public class Deadline extends Task implements RecurringTask {
    private LocalDateTime by;
    private boolean isRecurring;

    /**
     * Initializes a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.isRecurring = false;
    }

    /**
     * Gets the string representation of the recurring status for this task.
     *
     * @return A string indicating whether the task is recurring or not.
     */
    public String getRecurring() {
        return isRecurring ? "[R]" : "[ ]";
    }

    /**
     * Returns a string representation of the Deadline task,
     * including its status icon, description, and deadline.
     *
     * @return A string representing the Deadline task with its deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String dateTimeBy = this.by.format(customFormatter);
        return "[D]" + this.getRecurring() + super.toString() + " (by: " + dateTimeBy + ")";
    }

    /**
     * Returns a string representation of the Deadline task for saving,
     * including task type, completion status, description, and deadline.
     *
     * @return A string representing the Deadline task for saving in text file.
     */
    @Override
    public String toSave() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String dateTimeBy = this.by.format(customFormatter);
        return "D | " + (this.isRecurring ? "1" : "0") + " | " + (super.isDone ? "1" : "0") + " | "
                + super.description + " | " + dateTimeBy;
    }

    /**
     * Makes the task recurring and returns a string representation.
     *
     * @return A string indicating the task has been marked as recurring.
     */
    @Override
    public String makeRecur() {
        this.isRecurring = true;
        return this.toString();
    }

    /**
     * Gets the end date and time for the task.
     *
     * @return The end date and time for the task.
     */
    @Override
    public LocalDateTime getEndDateTime() {
        return this.by;
    }

    /**
     * Checks if the task is recurring.
     *
     * @return True if the task is recurring, false otherwise.
     */
    @Override
    public boolean isRecurring() {
        return this.isRecurring;
    }

    /**
     * Adjusts the deadline for a recurring task.
     */
    @Override
    public void adjustDeadline() {
        this.by = this.by.plusWeeks(1);
    }
}
