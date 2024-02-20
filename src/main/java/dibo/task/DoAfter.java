package dibo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DoAfter class represents a do-after task of the user.
 */
public class DoAfter extends Task {
    private LocalDate after;

    /**
     * Constructs a new DoAfter object with the specified parameters.
     *
     * @param description The String description of the task.
     * @param after The LocalDate object of date the task needs to be completed after.
     */
    public DoAfter(String description, LocalDate after) {
        super(description);
        this.after = after;
    }

    @Override
    public String toString() {
        String outputAfterString = this.after.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[A]" + super.toString() + " (after: " + outputAfterString + ")";
    }

    @Override
    public String getSaveFormat() {
        String outputAfterString = this.after.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "do-after | " + super.getSaveFormat() + " | " + outputAfterString;
    }
}
