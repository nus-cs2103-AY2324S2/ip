package unim.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines - Represents a task with a deadline, a subclass of Task.
 */

public class Deadline extends Task {
    private String byDate;
    private LocalDateTime d1;

    /**
     * Creates a Deadlines task with a description and a deadline specified as a String.
     *
     * @param description The description of the task.
     * @param byDate      The deadline of the task as a String.
     */
    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Creates a Deadlines task with a description and a deadline specified as a LocalDate.
     *
     * @param description The description of the task.
     * @param d1          The deadline of the task as a LocalDate.
     */
    public Deadline(String description, LocalDateTime d1) {
        super(description);
        this.d1 = d1;
    }

    @Override
    public String toString() {
        if (d1 != null) {
            return super.getStatusIcon() + "[D] " + getDescription()
                    + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
        }
        return super.getStatusIcon() + "[D] " + getDescription() + " (by: " + byDate + ")";
    }
}
