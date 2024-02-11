package tsundere.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline object.
 */
public class Deadline extends Task {

    private static final String TYPE = "D";
    protected String by;
    protected LocalDate date;


    /**
     * Initializes Deadline task with its name.
     *
     * @param description Name of Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null : "by should not be null";
        this.by = by;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM d yyyy");
        this.date = LocalDate.parse(by, f);
    }

    /**
     * Returns formatted String for storage purposes.
     *
     * @return formatted saveString.
     */
    @Override
    public String toSaveString() {
        return TYPE + "," + super.toSaveString() + "," + by + "," + this.getTagString();
    }

    /**
     * Returns String representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
