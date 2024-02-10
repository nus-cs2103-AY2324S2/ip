package guanguan;

import java.time.LocalDate;

/**
 * Represents a Deadline class inherited from Task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for a Deadline.
     *
     * @param description description of the deadline
     * @param by          due date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String saveToText() {
        return String.format("D | %s | %s | %s", this.isDone ? 1 : 0, this.description,
                Utils.convertDateTimeToString(this.by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Utils.printTime(by) + ")";
    }
}
