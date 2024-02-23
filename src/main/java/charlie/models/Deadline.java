package charlie.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate by;
    protected String byString;

    /**
     * constructor for task Deadline
     * @param description description of task deadline in string form
     * @param byString time by which the task deadline is due, is in ISO_LOCAL_DATE format (yyyy-MM-dd)
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = LocalDate.parse(byString); // Assumes input is in ISO_LOCAL_DATE format (yyyy-MM-dd)
        this.byString = byString;
    }

    /**
     * @return description string for deadline event
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    public String getBy() {
        return this.byString;
    }
}


