package youdon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the type icon for the deadline task.
     *
     * @return The type icon for the deadline task.
     */
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.deadline.format(formatter);
        return "[" + this.getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description.trim()
                + " (by: " + formattedDateTime + ")";
    }
}
