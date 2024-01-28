package duke;
import java.time.LocalDate;

/**
 * Event task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event
     *
     * @param description Name of task.
     * @param from Starting LocalDate of task.
     * @param to Ending LocalDate of task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
