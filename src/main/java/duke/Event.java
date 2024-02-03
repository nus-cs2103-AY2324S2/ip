package duke;
import java.time.LocalDateTime;

/**
 * The Event class represents a event, starting date and ending date.
 * It has information about the event and it is a subclass of the Task class.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for Event class.
     *
     * @param description The description of the task.
     * @param from The starting date or time of the task.
     * @param to The ending date or time of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string format of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
