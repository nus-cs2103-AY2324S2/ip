package chingu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This class is implementation of Task which contains description, from and to.
 */
public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;

    /**
     * Create an instance of Events class which contains description of the task,
     * start date and end dates of the event
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to, String priority) {
        super(description, priority);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    /**
     * Returns String of task detail with type of Task (Events),
     * events date (from) and (to) - in form of MMM d yyyy -, description.
     *
     * @return Task detail
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: "+ to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
