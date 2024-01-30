import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 * It has information about the deadline and it is a subclass of the Task class.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns a string format of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}

