package martin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate due;
    private final String DATE_TIME_FORMAT = "MMM d yyyy";

    /**
     * Constructs a Deadline object with the given description and due date.
     *
     * @param description The description of the deadline.
     * @param due         The due date of the deadline.
     */
    Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        String formattedDate = this.due.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Returns a string representation of the Deadline object in a format suitable
     * for saving to a file.
     *
     * @return A string representation of the Deadline object for file storage.
     */
    @Override
    public String toFileString() {
        String taskStatus = this.getIsDone() ? "1" : "0";
        return "D | " + taskStatus + " | " + this.getDescription() + " | " + this.due;
    }
}
