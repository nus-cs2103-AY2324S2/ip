package capone.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * Extends the Task class.
 *
 * @author Tay Rui-Jie
 */
public class Deadline extends Task {
    /**
     * The deadline in string format.
     * Used when deadline is not provided in LocalDateTime format.
     */
    private String deadlineString;

    /** The deadline in LocalDateTime format. */
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified description,
     * status, and deadline in string format.
     *
     * @param description The description of the task.
     * @param status      The completion status of the task.
     * @param deadline    The deadline of the task in string format.
     */
    public Deadline(String description, boolean status, String deadline) {
        super(TaskType.DEADLINE, description, status);
        this.deadlineString = deadline;
    }

    /**
     * Constructs a Deadline task with the specified description, status,
     * and deadline in LocalDateTime format.
     *
     * @param description The description of the task.
     * @param status      The completion status of the task.
     * @param deadline    The deadline of the task in LocalDateTime format.
     */
    public Deadline(String description, boolean status, LocalDateTime deadline) {
        super(TaskType.DEADLINE, description, status);
        this.deadline = deadline;
    }

    /**
     * Gets the formatted deadline as a string.
     *
     * @return The formatted deadline string.
     */
    public String getDeadline() {
        if (this.deadline != null) {
            return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return this.deadlineString;
    }

    /**
     * Overrides the toString method to represent the Deadline task as a string.
     *
     * @return A formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
