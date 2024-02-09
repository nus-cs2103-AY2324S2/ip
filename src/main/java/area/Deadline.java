package area;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate details;

    /**
     * Create Deadline object
     * 
     * @param description
     * @param by
     */
    public Deadline(String description,
            String by) {
        super(description);
        this.details = LocalDate.parse(by);
    }

    /**
     * returns a string that displays the deadline details.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + details.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
