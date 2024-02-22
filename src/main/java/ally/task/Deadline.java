package ally.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstraction of Deadline Tasks.
 * Child class of Task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Public Constructor of Deadline object
     * Required format: {@code name-of-event /by YYYY-MM-DD}
     * @param desc description of Deadline in required format
     */
    public Deadline(String desc) {
        String[] splitDesc = desc.split(" /by ");
        this.description = splitDesc[0];
        this.by = LocalDate.parse(splitDesc[1]);
    }

    /**
     * Get string representation of Deadline
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
