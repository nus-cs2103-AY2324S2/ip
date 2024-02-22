package ciara.storage;

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
        super(description, TaskType.DEADLINE, false, false);
        this.dueDate = dueDate;
    }

    /**
     * Create a Deadline task
     *
     * @param description Description of the deadline
     * @param dueDate     Due date of the deadline
     * @param isCompleted Status of the deadline
     * @param isArchived  Visibility of the deadline
     */
    public Deadline(String description, Instant dueDate, boolean isCompleted, boolean isArchived) {
        super(description, TaskType.DEADLINE, isCompleted, false);
        this.dueDate = dueDate;
    }

    /**
     * Create a Deadline task
     *
     * @param description Description of the deadline
     * @param dueDate     Due date of the deadline (in epoch Milliseconds)
     * @param isCompleted Status of the deadline
     * @param isArchived  Visibility of the deadline
     */
    public Deadline(String description, long dueDate, boolean isCompleted, boolean isArchived) {
        super(description, TaskType.DEADLINE, isCompleted, isArchived);
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
     *
     * @return True if deadline is on the specified date, false otherwise
     */
    public boolean isOn(Instant date) {
        LocalDate dueDateLocal = this.dueDate.atZone(ZoneId.of("+8")).toLocalDate();
        LocalDate dateLocal = date.atZone(ZoneId.of("+8")).toLocalDate();

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
                .withZone(ZoneId.of("+8"));

        return String.format("[D]%s (by: %s)", super.toString(), formatter.format(dueDate));
    }

    /**
     * Indicates whether some other object is "equal to" this Deadline
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline task = (Deadline) obj;

            return super.equals(task) && this.dueDate.equals(task.dueDate);
        }

        return false;
    }
}
