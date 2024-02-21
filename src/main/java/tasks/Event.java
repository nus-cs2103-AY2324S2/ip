package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Action for creating an Event
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Actions1.Event class
     * @param description action to be made
     * @param from Starting Time
     * @param to Ending Time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @return get event starting date
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * @return get event ending date
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * @return task output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:"
                + this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    /**
     * Sort based on from timing
     * if same, sort based on to timing
     * if same, sort alphabetically
     * @param task the object to be compared.
     * @return result of the comparison
     */
    @Override
    public int compareTo(Task task) {
        Event event = (Event) task;
        int result = this.from.compareTo(event.from);
        if (result == 0) {
            result = this.to.compareTo(event.to);
            if (result == 0) {
                result = this.getExecute().compareTo(event.getExecute());
            }
        }
        return result;
    }
}
