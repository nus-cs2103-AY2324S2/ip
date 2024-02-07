package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline object.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for a Deadline object.
     * @param msg Description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String msg, String by) {
        super(msg);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
