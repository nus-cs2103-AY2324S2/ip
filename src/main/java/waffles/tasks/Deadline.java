package waffles.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline in the Waffles chatbot application.
 * It extends the Task class.
 */
public class Deadline extends Task {

    private static final String DEADLINE_MESSAGE = "[D]%s (by: %s)";
    private static final String DEADLINE_FILE_TEMPLATE = "D | %s | %s";
    private final LocalDateTime deadline;
    private final DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("EEE HHmm dd/MM/yyyy");

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline of the task in the format "yyyy/MM/dd HHmm".
     */
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter acceptFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        this.deadline = LocalDateTime.parse(deadline, acceptFormatter);
    }

    /**
     * Returns the string representation of the deadline task for saving to a file.
     *
     * @return The string representation of the deadline task for saving to a file.
     */
    public String toTaskFileTemplate() {
        return String.format(DEADLINE_FILE_TEMPLATE, super.toTaskFileTemplate(), deadline);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format(DEADLINE_MESSAGE, super.toString(), deadline.format(returnFormatter));
    }
}
