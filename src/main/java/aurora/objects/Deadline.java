package aurora.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** The Deadline class represents a task with a specified date set as the deadline. */
public class Deadline extends Task{

    /** Date at which the deadline expires. */
    private LocalDateTime date;

    private static final String TASK_TYPE_FOR_FILE = "D";
    private static final String TASK_TYPE = "[D]";

    /**
     * Constructs a Deadline object.
     *
     * @param description: Description of the deadline.
     * @param date: Date at which the deadline expires.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Returns a String representation of the date LocalDateTime object.
     *
     * @return Returns String representation of the date LocalDateTime object.
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
