package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a deadline in the EMIS application.
 * It is a subclass of the Task class and provides additional functionality specific to tasks with deadlines.
 */
public class Deadline extends Task {
    /** The deadline string in the format 'dd-MM-yyyy HHmm'. */
    protected String by;

    /**
     * Constructs a new Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        formatDate();
    }

    /**
     * Constructs a new Deadline object with the specified completion status, description, and deadline.
     *
     * @param isDone The completion status of the deadline task.
     * @param description The description of the deadline task.
     * @param by The deadline.
     */
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
        formatDate();
    }

    /**
     * Formats the deadline date and time into a standard format.
     * Attempts various date and date-time patterns to parse the deadline string.
     */
    public void formatDate() {
        LocalDateTime doByDateTime = null;
        String[] datePatterns = {"yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "MMM dd yyyy", "dd MMM yyyy"};
        String[] datetimePatterns = {"yyyy-MM-dd HHmm", "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm", "MMMM dd yyyy HHmm"};
        LocalDate ld = null;

        for (String dtpattern : datetimePatterns) {
            try {
                doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern(dtpattern));
                this.by = doByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                break;
            } catch (DateTimeParseException e) {
            }
        }

        if (doByDateTime == null) {
            for (String pattern : datePatterns) {
                try {
                    ld = LocalDate.parse(this.by, DateTimeFormatter.ofPattern(pattern));
                    this.by = ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    break;
                } catch (DateTimeParseException e) {
                }
            }
        }
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
        return "[D]" + super.toString() + " (by: " + this.by + ")";
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
        && this.by.equals(otherTask.by);
    }
}
