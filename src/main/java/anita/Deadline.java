package anita;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class inherits from the Task class.
 * A type of task which can be done by the user.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * The constructor for the Deadline class.
     * Creates a Deadline task.
     *
     * @param description The raw user input.
     */
    public Deadline(String description, String by, String status) {
        super(description, status);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

