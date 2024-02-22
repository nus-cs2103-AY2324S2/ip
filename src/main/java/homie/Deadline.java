package homie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends the Task class.
 * Can specify the Local Date Time to keep track of
 * when to complete deadline by.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline class
     * @param description For the deadline task
     * @param by This is the Local Date Time to complete the deadline by
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")) + ")";
    }
}
