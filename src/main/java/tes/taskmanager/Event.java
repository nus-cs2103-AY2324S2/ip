package tes.taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which should be done within a period.
 */
public class Event extends Task {
    protected LocalDateTime from; // starting time of the task
    protected LocalDateTime to; // ending time of the task

    /**
     * Constructs a task which should be done within a certain period.
     *
     * @param description Description of the event.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String from1 = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String to1 = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "E" + super.toString() + " | from: " + from1 + " to: " + to1;
    }
}
