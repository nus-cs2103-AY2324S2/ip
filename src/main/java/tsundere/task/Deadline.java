package tsundere.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected static final String type = "D";

    /**
     * Initializes Deadline task with its name.
     *
     * @param description Name of Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
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
        return type + "," + super.toSaveString() + "," + by;
    }

    /**
     * Returns String representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
