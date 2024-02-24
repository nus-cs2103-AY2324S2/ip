package sam.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sam.SamException;

/**
 * Represents a deadline task.
 *
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime date;
    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description the description of the Deadline task
     * @param by the due date/time of the Deadline task
     * @throws SamException if there is an issue with the provided parameters
     */
    public Deadline(String description, String by) throws SamException {
        super(description);

        if (by.isBlank()) {
            throw new SamException("No due date specified.");
        }

        this.date = LocalDateTime.parse(by, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, this.description, date.format(INPUT_FORMATTER));
    }
}
