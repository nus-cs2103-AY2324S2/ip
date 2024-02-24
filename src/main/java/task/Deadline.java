package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline in the EMIS application.
 * It is a subclass of the Task class and provides additional functionality specific to tasks with deadlines.
 */
public class Deadline extends Task {
    /** The deadline string in the format 'yyyy-MM-dd HHmm'. */
    protected String by;

    /** The deadline date and time represented as a LocalDateTime object. */
    protected LocalDateTime doByDateTime;

    /**
     * Constructs a new Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline in the format 'yyyy-MM-dd HHmm'.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        this.doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Constructs a new Deadline object with the specified completion status, description, and deadline.
     *
     * @param isDone The completion status of the deadline task.
     * @param description The description of the deadline task.
     * @param by The deadline in the format 'yyyy-MM-dd HHmm'.
     */
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
        this.doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Deadline object for storage purposes.
     *
     * @return A string representing the Deadline object in the format 'D | [Task] | [Deadline]'.
     */
    @Override
    public String storeString() {
        return "D | " + super.storeString() + " | " + this.by;
    }

    /**
     * Returns a string representation of the Deadline object for display purposes.
     *
     * @return A string representing the Deadline object in the format '[D][Task] (by: [Deadline])'.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.doByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Checks if this Deadline object is equal to another object.
     *
     * @param other The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Deadline otherTask = (Deadline) other;
        return this.description.equals(otherTask.description) 
        && this.by.equals(otherTask.by)
        && this.doByDateTime.equals(otherTask.doByDateTime);
    }
}
