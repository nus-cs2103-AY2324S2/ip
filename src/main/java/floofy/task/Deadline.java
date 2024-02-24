package floofy.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
/**
 * Represents an deadline task.
 */
public class Deadline extends Task {

    /** The date representing when the deadline has to be completed by */
    protected LocalDate by;

    /**
     * Constructs a new object of the Deadline class.
     * @param description The description of the deadline.
     * @param by The date representing when the deadline has to be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the date from LocalDate to String format.
     *
     * @param date The date representing when the deadline has to be completed by.
     * @return The date in a string format.
     */
    public String getDateString(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    /**
     * Returns the String form of the task type (Deadline) - "D".
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the String of a Deadline task in its file format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + String.format(" | %s", getDateString(this.by));
    }

    /**
     * Returns a string form of a Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}