package missa.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * missa.task.Deadline class for tasks with specified deadline.
 */
public class Deadline extends Task {
    /** missa.task.Deadline of task recorded */
    private LocalDateTime deadline;

    /**
     * Create a new deadline.
     *
     * @param task Content of task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm")) + ")";
    }

    @Override
    public String getData() {
        return "D" + super.getData() + " | "
                + this.deadline;
    }
}
