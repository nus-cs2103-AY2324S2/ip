package nollid.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a task with a specified deadline.
 * It extends the Task class and includes additional functionality for handling deadlines.
 */
public class Deadline extends Task {
    public static final String USAGE_HINT = "Usage: deadline [task description] /by [d/m/yyyy] {hh:mm 24hr format}";
    public static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    protected static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);
    protected static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object with a description and a deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task represented as LocalDateTime.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String getDeadlineString() {
        return this.deadline.format(DATE_OUTPUT_FORMAT) + " " + this.deadline.format(TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")";
    }
}
