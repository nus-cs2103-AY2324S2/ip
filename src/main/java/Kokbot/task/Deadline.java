package Kokbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {

    /**
     * Due date of the Deadline
     */
    protected LocalDateTime dueDate;

    /**
     * Constructor for Deadline with LocalDateTime
     *
     * @param description Description of the Deadline
     * @param newDueDate  Due date of the Deadline in LocalDateTime format
     */
    public Deadline(String description, LocalDateTime newDueDate) {
        super(description);
        this.dueDate = newDueDate;
    }

    /**
     * Constructor for Deadline with String
     *
     * @param description Description of the Deadline
     * @param newDueDate  Due date of the Deadline in String format
     */
    public Deadline(String description, String newDueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(newDueDate);
    }

    /**
     * Returns the type of the Deadline
     *
     * @return Type of the Deadline
     */
    @Override
    public String getType() {
        return "D";
    }

    public LocalDateTime getDateTime() {
        return this.dueDate;
    }
    /**
     * Returns the due date of the Deadline in String format
     */
    public String formatDueDate() {
        return this.dueDate.format(DateTimeFormatter.ofPattern(this.dateTimeFormat));
    }

    /**
     * Represents the Deadline in String format
     *
     * @return String format of the Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.formatDueDate());
    }

    /**
     * Represents the Deadline in String format for saving to file
     *
     * @return String format of the Deadline for saving to file
     */
    @Override
    public String toFileString() {
        return String.format("D,%s,%s", super.toFileString(), this.dueDate);
    }

}
