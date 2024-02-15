package lilybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class, a subtype of task.
 * Deadlines are specified by a due date.
 */
public class Deadline extends Task {

    /** Due dtae of deadline */
    protected String by;

    /** Due date format */
    protected LocalDate date;

    /**
     * Constructor for Deadline.
     * @param description Contents of deadline entered by the user.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim(); // Trim leading and trailing whitespace
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        //return "[D]" + super.toString() + "(by:" + getBy() + ")";
        return "[D]" + super.toString()
                + "(by:" + getDate()
                .format(DateTimeFormatter
                        .ofPattern("MMM dd yyyy"))
                + ")";
    }

    public String getBy() {
        return by;
    }

    public LocalDate getDate() {
        return date;
    }
}
