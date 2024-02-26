package talktomeorilldie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Constructor for Deadline.
     * @param description Description of the deadline.
     * @param by Date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.date = by;
    }

    /**
     * Returns the date and time of the deadline.
     * @return Date and time of the deadline.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns the string representation of the deadline.
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mma")) + ")";
    }

    /**
     * Returns the string representation of the deadline for saving to file.
     * @return String representation of the deadline for saving to file.
     */
    @Override
    public String toSaveString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
