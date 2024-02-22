package lilybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class, a subtype of task.
 * Deadlines are specified byDate a due date.
 */
public class Deadline extends Task {

    /** Due date of deadline */
    protected String byDate;

    /** Formatted Due date */
    protected LocalDate date;

    /**
     * Constructs a Deadline object.
     *
     * @param description Contents of deadline entered byDate the user.
     * @param byDate Due date of the deadline.
     */
    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate.trim(); // Trim leading and trailing whitespace
        this.date = LocalDate.parse(byDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(byDate:" + getDate()
                .format(DateTimeFormatter
                        .ofPattern("MMM dd yyyy"))
                + ")";
    }

    public String getByDate() {
        return byDate;
    }

    public LocalDate getDate() {
        return date;
    }
}
