package area;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a child class of Task. It is specifically for tasks that are
 * required to be completed in a certain amount of time.
 */
public class Deadline extends Task {

    protected LocalDate details;

    /**
     * Creates Deadline object
     * 
     * @param description Description of Deadline
     * @param by Separator to separate deadline and Description
     */
    public Deadline(String description,
            String by) {
        super(description);
        this.details = LocalDate.parse(by);
    }

    /**
     * Returns a string that displays the deadline details.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + details.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
