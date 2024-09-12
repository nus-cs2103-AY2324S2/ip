package kervyn.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is an extension of the Task class with an additional deadline date.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with a description, status, and deadline.
     *
     * @param description The description of the deadline task.
     * @param isCompleted The completion status of the deadline task.
     * @param deadline The LocalDateTime representing the deadline of the task.
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime deadline) {
        super(description, isCompleted, Type.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Gets the formatted deadline of the task.
     *
     * @return A string representing the formatted deadline (e.g., "Jan 1 2020, 5PM").
     */
    public String getDeadline() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        return formattedDeadline;
    }

    /**
     * Converts the task to a string representation, including type, status, and description.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        char check = this.getCompleted() ? 'X' : ' ';
        return "\t[" + this.getCapitalType() + "]" + " [" + check + "] " + this.getDescription() + " (by: " + this.getDeadline() + ")";
    }

}
