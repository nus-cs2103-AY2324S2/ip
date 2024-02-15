package duke.task;


import java.time.LocalDate;

/**
 * Represents a task with a deadline in the Duke application.
 */
public class Deadline extends Task {

    /**
     * The deadline date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline date of the task.
     *
     * @return The deadline date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "D";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.getDescription().equals(deadline.getDescription()) && this.getBy().equals(deadline.getBy());
        }
        return false;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getDayOfMonth()
                + " " + by.getMonth() + " " + by.getYear() + ")";

    }
}
