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
     * Create Deadline object
     * @param description
     * @param by
     */
    public Deadline(String description,
            String by) {
        super(description);
        this.details = LocalDate.parse(by);
        assert this.details.isAfter(LocalDate.now()) : "Deadline must be in the future";
    }
    

    /**
     * returns a string that displays the deadline details.
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + details.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
