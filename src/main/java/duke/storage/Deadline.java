package duke.storage;

import java.time.Instant;

/**
 * The Deadline class defines a 'Deadline' task used for the application
 *
 * @author Ryan NgWH
 */
public class Deadline extends Task {
    /**
     * Due date/time of the deadline task
     */
    private Instant dueDate;

    /**
     * Create a Deadline task
     *
     * @param description Description of the deadline
     * @param dueDate     Due date of the deadline
     */
    public Deadline(String description, Instant dueDate) {
        super(description, TaskType.DEADLINE, false);
        this.dueDate = dueDate;
    }

    /**
     * Create a Deadline task
     *
     * @param description Description of the deadline
     * @param dueDate     Due date of the deadline
     * @param isDone      Status of the deadline
     */
    public Deadline(String description, Instant dueDate, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Create a Deadline task
     *
     * @param description Description of the deadline
     * @param dueDate     Due date of the deadline (in epoch Milliseconds)
     * @param isDone      Status of the deadline
     */
    public Deadline(String description, long dueDate, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.dueDate = Instant.ofEpochMilli(dueDate);
    }

    /**
     * Get the due date of the deadline (in epoch milliseconds)
     *
     * @return Due date of the deadline
     */
    public long getDueDate() {
        return this.dueDate.toEpochMilli();
    }

    /**
     * String representation of a Deadline
     *
     * @return String representation of the Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
