package scribbles.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline object with the specified description and deadline.
     *
     * @param description description of deadline.
     * @param isCompleted true if deadline has been completed.
     * @param by date/time of deadline.
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Gets the deadline in LocalDateTime and changes it to String format.
     *
     * @return The deadline in String format.
     */
    public String getByString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.by.format(dateTimeFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getByString() + ")";
    }
}
