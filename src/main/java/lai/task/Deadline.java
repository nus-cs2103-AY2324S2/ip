package lai.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline, which is a task with an additional due date.
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a new Deadline with the specified description and due date.
     * The due date is parsed from a string and the task is initially marked as not done.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline in ISO-8601 format (e.g., 2023-12-31).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructs a new Deadline with the specified description, initial completion status, and due date.
     * If the due date string cannot be parsed in ISO-8601 format, an attempt is made to parse it in "dd MMM yyyy"
     * format.
     *
     * @param description The description of the deadline.
     * @param isDone The initial completion status of the deadline. True if the task is initially done, false otherwise.
     * @param by The due date of the deadline. Expected to be in ISO-8601 format, but "dd MMM yyyy" format is attempted
     *           if parsing fails.
     * @throws DateTimeParseException If the due date string cannot be parsed in either ISO-8601 or "dd MMM yyyy"
     * format.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    /**
     * Returns a string representation of the deadline, including its type, completion status, description, and due
     * date.
     *
     * @return A string in the format "[D][completion_status] description (by: due_date)", where completion_status is
     * "X" if the task is done, or a space character otherwise, and due_date is formatted as "dd MMM yyyy".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
