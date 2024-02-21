package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates task for Deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline task.
     *
     * @param description task to be taken
     * @param by time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the due date of the task.
     *
     * @return Date of the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Overrides the output of deadline tasks.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }

    /**
     * Overrides the comparison to sort deadline list based on due date.
     *
     * @param task the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Task task) {
        Deadline event = (Deadline) task;
        int result = this.by.compareTo(event.by);
        if (result == 0) {
            result = this.getExecute().compareTo(event.getExecute());
        }
        return result;
    }
}
