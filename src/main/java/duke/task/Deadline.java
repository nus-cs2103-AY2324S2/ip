/**
 * The Deadline class represents a task with a specific deadline date and time.
 * It is a subclass of the Task class.
 */
package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    /**
     * Constructs a Deadline object with a description and a specific deadline date and time.
     *
     * @param description A description of the deadline task.
     * @param byDateTime  The deadline date and time.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Constructs a Deadline object with a description and a specific deadline date (time is set to midnight).
     *
     * @param description A description of the deadline task.
     * @param byDate      The deadline date.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDateTime = byDate.atStartOfDay();
    }

    /**
     * Converts the Deadline object to a string that can be saved to a file.
     *
     * @return A formatted string representing the Deadline object.
     */
    @Override
    public String toFileString() {
        // Format: D | [Status] | [Description] | [Due Date]
        return "D | " + getStatusNumber() + " | " + description + " | " + byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Converts the Deadline object to a user-friendly string representation.
     *
     * @return A string representing the Deadline object in a user-friendly format.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[D]").append(super.toString()).append(" (by: ").append(byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

        if (byDateTime.toLocalTime().getHour() != 0 || byDateTime.toLocalTime().getMinute() != 0) {
            builder.append(" at ").append(byDateTime.format(DateTimeFormatter.ofPattern("hh:mm a")));
        }

        builder.append(")");

        return builder.toString();
    }
}
