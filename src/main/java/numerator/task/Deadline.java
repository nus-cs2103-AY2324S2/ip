package numerator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task
 */
public class Deadline extends numerator.task.Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and by
     *
     * @param description should contain information about the task
     * @param by          should contain information about the deadline
     * @throws DateTimeParseException if the date and time is not in the correct format
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = Task.parseStringToLocalDatetime(by);
    }


    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                Task.parseLocalDateTimeToString(this.by)
        );
    }

    /**
     * Returns a string with task details to be saved in the file
     *
     * @return a string to be saved in the file
     */
    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                Task.parseLocalDateTimeToString(this.by)
        );
    }
}
