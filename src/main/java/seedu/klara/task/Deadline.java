package seedu.klara.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the <code>Deadline</code> class to store information
 * about the user-created deadline e.g., <code>by</code> when
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for <code>Deadline</code>.
     * @param description Represents description of deadline
     * @param by Represents time limit for deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Represents overridden toString method for printing <code>Deadline</code>> details.
     * @return details of type <code>String</code>
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
