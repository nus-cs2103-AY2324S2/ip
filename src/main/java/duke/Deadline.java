package duke;
import java.time.LocalDate;

/**
 * Deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;
    /**
     * Constructor for Deadline.
     *
     * @param description Name of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
