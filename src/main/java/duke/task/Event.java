package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an Event task that stores the description, duration and status of completion.
 */
public class Event extends Task {

    protected LocalDateTime from;

    protected LocalDateTime to;

    /**
     * Initlialises an Event with the description, duration and status of completion.
     * @param description event description
     * @param from starting time
     * @param to ending time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        String strFrom = convertDate(from);
        String strTo = convertDate(to);
        return "[E]" + super.getDescription() + " (from: " + strFrom + " to: " + strTo + ")";
    }

    @Override
    public LocalDateTime getStart() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
