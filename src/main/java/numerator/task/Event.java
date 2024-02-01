package numerator.task;

import java.time.LocalDateTime;

/**
 * Represents an Event task
 */
public class Event extends numerator.task.Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, from and to
     *
     * @param description should contain information about the task
     * @param from        should contain information about the start date
     * @param to          should contain information about the end date
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = Task.parseStringToLocalDatetime(from);
        this.to = Task.parseStringToLocalDatetime(to);
    }


    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                this.description,
                Task.parseLocalDateTimeToString(this.from),
                Task.parseLocalDateTimeToString(this.to)
        );
    }

    /**
     * Returns a string with task details to be saved in the file
     *
     * @return a string to be saved in the file
     */
    @Override
    public String getSaveString() {
        return String.format(
                "E | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                Task.parseLocalDateTimeToString(this.from),
                Task.parseLocalDateTimeToString(this.to)
        );
    }
}
