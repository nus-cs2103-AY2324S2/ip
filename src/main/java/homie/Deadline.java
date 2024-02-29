package homie;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class that extends the Task class. Handles all deadline related tasks.
 * Can specify the Local Date Time to keep track of when to complete deadline by.
 */
public class Deadline extends Task implements Serializable {

    protected LocalDateTime dueDateTime;

    /**
     * Constructor for Deadline class to create a new deadline instance.
     *
     * @param description The description of deadline task.
     * @param by This is the Local Date Time to complete the deadline by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dueDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd MM yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: "
                + this.dueDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")) + ")";
    }
}
