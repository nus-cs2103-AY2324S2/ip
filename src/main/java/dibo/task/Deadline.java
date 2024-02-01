package dibo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent a deadline.
 */
public class Deadline extends Task{
    private LocalDate by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task.
     * @param by The date the task needs to complete by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    /**
     * Returns the string representation for the deadline task,
     * usually for the display format in the ui.
     *
     * @return The string representation of the deadline task for displaying.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns the string representation of the deadline task,
     * usually for saving in the text file.
     *
     * @return The string representation of the deadline task for saving.
     */
    @Override
    public String getSaveFormat() {
        String outputByString = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "deadline | " + super.getSaveFormat() + " | " + outputByString;
    }
}
