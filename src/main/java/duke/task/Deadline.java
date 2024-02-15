package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline object with the given description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = getStatusIcon();

        String deadlineStatus = "[D][" + status + "] ";
        String desc = super.toString();
        String by = " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

        return deadlineStatus + desc + by;
    }

    @Override
    public String toFileString() {
        String deadlineStatus = "D | " + (isDone ? "1" : "0");
        String desc = " | " + description + " | ";

        return deadlineStatus + desc + by;
    }
}
