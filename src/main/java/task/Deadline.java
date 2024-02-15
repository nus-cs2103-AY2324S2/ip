package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(
            "[yyyy-MM-dd HH:mm][yyyy/MM/dd HH:mm][yyyy MM dd HH:mm][yyyy.MM.dd HH:mm]");
    private LocalDateTime by;

    /**
     * Constructor for Deadline.
     * @param description The description of the deadline.
     * @param by The date and time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        if (by.length() < 10 || (by.length() > 16 && by.length() != 64)) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (by.length() == 10) {
            by += " 00:00";
        }
        this.by = LocalDateTime.parse(by, this.FORMAT);
    }

    /**
     * Constructor for Deadline.
     * @param description The description of the deadline.
     * @param by The date and time of the deadline.
     * @param isDone Whether the deadline is done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        if (by.length() < 10 || (by.length() > 16 && by.length() != 64)) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (by.length() == 10) {
            by += " 00:00";
        }
        this.by = LocalDateTime.parse(by, this.FORMAT);
    }

    /**
     * Returns the date and time of the deadline.
     * @return The date and time of the deadline.
     */
    public String getBy() {
        return this.by.format(this.FORMAT);
    }

    /**
     * Returns the string representation of the deadline.
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
