// Deadline.java
package bentley;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * Extends the Task class.
 */
public class Deadline extends Task {

    /**
     * The deadline date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a Deadline object with the given description and deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a formatted string representation of the Deadline object.
     *
     * @return A string containing task type, description, and deadline date.
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "D |" + super.toString() + " | " + formattedDate;
    }
}
