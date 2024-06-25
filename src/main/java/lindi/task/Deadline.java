package lindi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Initialises the deadline task
     *
     * @param description description of the deadline
     * @param by datetime of the deadline following '{@value SAVE_LOAD_DT_FORMAT_STRING}' format
     * @throws CreateDeadlineException if param by provided is in the wrong format
     */
    public Deadline(String description, String by) throws CreateDeadlineException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, Task.SAVE_LOAD_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CreateDeadlineException(String.format("Deadline /by argument in the wrong format. Use "
                    + "format '%s' instead. Not saving seconds and below :)", Task.SAVE_LOAD_DT_FORMAT_STRING));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.DISPLAY_DATETIME_FORMAT) + ")";
    }

    @Override
    public String toParsedFormat() {
        return String.format("D | %c | %s | %s",
                this.isDone ? 'y' : 'n', this.description,
                        this.by.format(Task.SAVE_LOAD_DATETIME_FORMAT));
    }
}
