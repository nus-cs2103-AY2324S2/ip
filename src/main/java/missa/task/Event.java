package missa.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * missa.task.Event class stores tasks that have a duration.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Create a new event.
     *
     * @param task Content of event.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from "
                + this.from.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"))
                + ")";
    }

    @Override
    public String getData() {
        return "E" + super.getData() + " | " + this.from + " | " + this.to;
    }
}
