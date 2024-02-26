package yarr.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description, completion status and deadline.
 * This class provides methods to retrieve information about the task
 * to either be read or save to the hard disk.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    /**
     * Constructs a new Deadline object with the specified description and deadline.
     *
     * @param description a String representing the description of the deadline task
     * @param by a LocalDateTime representing the deadline for the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(stringFormat) + ")";
    }

    @Override
    public String getData() {
        return "D | " + super.getData() + " | " + by.format(dataFormat);
    }
}
