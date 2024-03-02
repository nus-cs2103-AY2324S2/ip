//// Solution below adapted by week2 iP Level-3 Partial solution
package ping.job;
import java.time.LocalDateTime;

/**
 * This class is used to create an event task
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * This is the constructor of the Event class
     * @param description the description of the event task
     * @param from the start time of the event task
     * @param to the end time of the event task
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
