package aurora.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task with a specified date set as the deadline.
 */
public class Deadline extends Task{

    /** Date at which the deadline expires. */
    private LocalDateTime date;

    private static final String TASK_TYPE_FOR_FILE = "D";
    private static final String TASK_TYPE = "[D]";

    /**
     * Constructor of deadline
     *
     * @param description: Description of the deadline.
     * @param date: Date at which the deadline expires.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Getter for date.
     *
     * @return The deadline.
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Formats the local datetime to string.
     *
     * @return Date in String format.
     */
    private String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return date.format(formatter);
    }

    @Override
    public String toFileString() {
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String formattedDate = dateToString();
        return TASK_TYPE_FOR_FILE + " | " + isDone + " | " + description + " | " + formattedDate;
    }

    @Override
    public String toString() {
        String formattedDate = dateToString();
        String deadlineString = TASK_TYPE + super.toString() + " (by: " + formattedDate + ")";
        return deadlineString;
    }
}
