package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create task for Deadline
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor
     * @param description task to be taken
     * @param by time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * @return Date of the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * @return task output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }

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
