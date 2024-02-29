package homie;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class used to create a Deadline Object.
 * A subclass of Task class.
 * Has a description and due date.
 */
public class Deadline extends Task implements Serializable {

    protected LocalDateTime dueDateTime;

    /**
     * The constructor for Deadline.
     *
     * @param description The description of Deadline object.
     * @param by This is the due date and time of the Deadline.
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
