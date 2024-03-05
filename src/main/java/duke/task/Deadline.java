package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@inheritDoc}
 *
 * In this subclass we have a deadline date for the task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs the class Deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fileString() {
        return "D/" + super.fileString() + "/" + this.by;
    }
}
