package dibo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent a do-after task.
 */
public class DoAfter extends Task {
    private LocalDate after;

    /**
     * Constructor for the DoAfter class.
     *
     * @param description The description of the task.
     * @param after The date the task needs to be done after.
     */
    public DoAfter(String description, LocalDate after) {
        super(description);
        this.after = after;
    }
    /**
     * Returns the string representation for the do-after task,
     * usually for the display format in the ui.
     *
     * @return The string representation of the date the task needs to be done after for displaying.
     */
    @Override
    public String toString() {
        String outputAfterString = this.after.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[A]" + super.toString() + " (after: " + outputAfterString + ")";
    }

    /**
     * Returns the string representation of the do-after task,
     * usually for saving in the text file.
     *
     * @return The string representation of the date the task needs to be done after for saving.
     */
    @Override
    public String getSaveFormat() {
        String outputAfterString = this.after.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "do-after | " + super.getSaveFormat() + " | " + outputAfterString;
    }
}
