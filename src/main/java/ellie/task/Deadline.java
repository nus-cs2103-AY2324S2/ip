package ellie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline, indicating that it needs to be done before a specific date/time.
 * Example: submit report /by 11/10/2019 5pm
 */
public class Deadline extends Task {
    private String dueDateString;
    private LocalDate dueDate;

    /**
     * Constructs a Deadline object with the given description and deadline date string.
     *
     * @param description   The description of the task with a deadline.
     * @param dueDateString The string representation of the deadline date.
     */
    public Deadline(String description, String dueDateString) {
        super(description);
        this.dueDateString = dueDateString;

        // parse date if it is in the correct format.
        try {
            dueDate = LocalDate.parse(dueDateString);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
    }

    /**
     * Constructs a Deadline object with the given description, completion status, and deadline date string.
     *
     * @param description   The description of the task with a deadline.
     * @param isDone        The completion status of the task (1 for done, 0 for not done).
     * @param dueDateString The string representation of the deadline date.
     */
    public Deadline(String description, int isDone, String dueDateString) {
        super(description, isDone);
        this.dueDateString = dueDateString;

        try {
            dueDate = LocalDate.parse(dueDateString);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
    }

    /**
     * Returns the task type identifier for a task with a deadline.
     *
     * @return The task type identifier ('D' for deadline).
     */
    @Override
    public char getTaskType() {
        return 'D';
    }

    /**
     * Returns a formatted string representation of the task with a deadline for listing.
     *
     * @return A formatted string representation of the task with a deadline,
     *     including completion status and description with the deadline date.
     */
    @Override
    public String listTaskString() {
        if (dueDate == null) {
            return "[D]" + super.listTaskString()
                    + " (by: " + this.dueDateString + ")";
        } else {
            return "[D]" + super.listTaskString()
                    + " (by: " + this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    /**
     * Returns the string representation of the deadline date.
     *
     * @return The string representation of the deadline date.
     */
    public String getDueDate() {
        return this.dueDateString;
    }

}
