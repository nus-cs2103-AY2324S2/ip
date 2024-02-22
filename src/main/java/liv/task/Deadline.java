package liv.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements a deadline as a type of task.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    /**
     * The pattern of DateTime that is printed to the user.
     */
    private static final String OUTPUT_PATTERN = "MMM dd yyyy HH:mm";

    /**
     * The constructor of the class.
     * @param description The description of the task.
     * @param by Date and time of the deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     * Uses [D] to denote deadline type.
     * Prints the Date and Time of the deadline along with the task.
     */
    @Override
    public String getDisplayedString() {
        return "[D]" + getStatusIcon() + " " + getDescription()
                + " (by: " + by.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN)) + ")";
    }

    /**
     * {@inheritDoc}
     * Uses [D] to denote deadline type.
     * Stores the Date and Time of the deadline along with the task.
     */
    @Override
    public String serializeTask() {
        return "[D] | " + getStatusIcon() + " | " + getDescription() + " | " + by.toString();
    }
}
