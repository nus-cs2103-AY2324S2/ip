package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a {@link Task} with a deadline to do by.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for a Deadline.
     * @param name name of the task
     * @param by deadline
     * @throws DateTimeParseException if deadline is not of format "yyyy-MM-dd HHmm".
     */
    public Deadline(String name, String by) throws DateTimeParseException {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm")) + ")";
    }
}
