package paimon.task;

import java.time.LocalDateTime;

/**
 * Represents a task that has a deadline. This class extends {@link Task} to include deadline functionality.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime endDate;

    /**
     * Constructs a DeadlineTask with the specified description and end date.
     *
     * @param description The task's description.
     * @param endDate     The deadline of the task, represented as a {@link LocalDateTime}.
     */
    public DeadlineTask(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
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
            return "[D][X] " + this.description + " (by: " + endDate.format(DATE_FORMAT) + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + endDate.format(DATE_FORMAT) + ")";
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
            return "D | 1 | " + this.description + " | " + this.endDate.format(DATE_FORMAT);
        } else {
            return "D | 0 | " + this.description + " | " + this.endDate.format(DATE_FORMAT);
        }
    }
}
