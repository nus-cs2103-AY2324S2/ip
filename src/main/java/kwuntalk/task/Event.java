package kwuntalk.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;


    /**
     * Constructor for an event task.
     *
     * @param task Task description.
     * @param fromDateTime DateTime which event starts.
     * @param toDateTime DateTime which event ends.
     */
    public Event(String task, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(task);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }


    /**
     * Format task to store in the Tasks File.
     *
     * @return String representation of the formatted task.
     */
    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", status, super.formatTask(), fromDateTime, toDateTime);
    }


    /**
     * Return the string representation of the task.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")),
                toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
