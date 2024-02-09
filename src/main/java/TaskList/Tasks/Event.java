package TaskList.Tasks;

import java.time.LocalDateTime;

import static utils.StringUtils.formatDateTime;

/**
 * Represents an event task.
 * A <code>Event</code> object corresponds to a task with a description and a time period
 * e.g., <code>"event project meeting /from 2-12-2019 1800 /to 2-12-2019 2000"</code>
 */
public class Event extends Task{
    LocalDateTime from;
    LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.from) + " to: " + formatDateTime(this.to) + ")";
    }

    public String save() {
        return "event " + super.description + " /from " + formatDateTime(this.from) + " /to " + formatDateTime(this.to);
    }
}
