package paimon.task;


import java.time.LocalDateTime;

/**
 * Represents a task that must be done after a certain date,
 * This class extends {@link Task} to include deadline functionality.
 */
public class DoAfterTask extends Task {
    private final LocalDateTime beginDate;

    /**
     * Constructs a DeadlineTask with the specified description and end date.
     *
     * @param description The task's description.
     * @param endDate     The deadline of the task, represented as a {@link LocalDateTime}.
     */
    public DoAfterTask(String description, LocalDateTime endDate) {
        super(description);
        this.beginDate = endDate;
    }

    /**
     * Returns the string representation of the deadline task, including its status (done or not done),
     * description, and deadline.
     *
     * @return A string indicating the task's type (D for Deadline), status, description, and deadline.
     */
    @Override
    public String getTask() {
        if (isDone) {
            return "[A][X] " + this.description + " (after: " + beginDate.format(DATE_FORMAT) + ")";
        } else {
            return "[A][ ] " + this.description + " (after: " + beginDate.format(DATE_FORMAT) + ")";
        }
    }

    /**
     * Returns the string representation of the deadline task for saving to a file. This includes
     * the task type (D), its status, description, and deadline formatted according
     * to {@link Task#DATE_FORMAT}.
     *
     * @return A string suitable for file storage.
     */
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "A | 1 | " + this.description + " | " + this.beginDate.format(DATE_FORMAT);
        } else {
            return "A | 0 | " + this.description + " | " + this.beginDate.format(DATE_FORMAT);
        }
    }
}
