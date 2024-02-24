package ficin.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline. This class extends {@link Task} to include
 * functionality for handling deadline dates and times.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter USER_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter USER_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    private final LocalDateTime byDateTime;

    /**
     * Constructs a new Deadline instance with a description and deadline date and time.
     *
     * @param description the description of the deadline task
     * @param byDateTime the deadline date and time
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Constructs a new Deadline instance with a description and a deadline date. The time is set to midnight.
     *
     * @param description the description of the deadline task
     * @param byDate the deadline date
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDateTime = byDate.atStartOfDay();
    }

    /**
     * Returns a string representation of the deadline suitable for saving to a file.
     * The format includes the task type, status, description, and deadline date and time.
     *
     * @return a formatted string representing the deadline for file storage
     */
    @Override
    public String toFileString() {
        return "D | " + getStatusNumber() + " | " + description + " | "
                + byDateTime.format(FILE_FORMATTER);
    }

    /**
     * Returns a string representation of the deadline for display to the user.
     * The format includes the task type, status, description, and a user-friendly
     * representation of the deadline date and time.
     *
     * @return a string representing the deadline in a format suitable for user viewing
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[D]").append(super.toString()).append(" (by: ")
                .append(byDateTime.format(USER_DATE_FORMATTER));

        if (byDateTime.toLocalTime().getHour() != 0 || byDateTime.toLocalTime().getMinute() != 0) {
            builder.append(" at ").append(byDateTime.format(USER_TIME_FORMATTER));
        }

        builder.append(")");

        return builder.toString();
    }
}
