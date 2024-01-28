package duke.storage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
     * Check if deadline is on the specified date
     *
     * @param date Date to check against
     * @return True if deadline is on the specified date, false otherwise
     */
    public boolean isOn(Instant date) {
        LocalDate dueDateLocal = this.dueDate.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateLocal = date.atZone(ZoneId.systemDefault()).toLocalDate();

        return dueDateLocal.equals(dateLocal);
    }

    /**
     * String representation of a Deadline
     *
     * @return String representation of the Deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mma")
                .withZone(ZoneId.systemDefault());

        return String.format("[D]%s (by: %s)", super.toString(), formatter.format(dueDate));
    }
}
