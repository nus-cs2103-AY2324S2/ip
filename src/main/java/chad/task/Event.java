package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a {@link Task} with starting and ending time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for a Event.
     * @param name name of event
     * @param from starting date and time of event
     * @param to ending date and time of event
     * @throws DateTimeParseException if date and time are not of format "yyyy-MM-dd HHmm".
     */
    public Event(String name, String from, String to) throws DateTimeParseException {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm")) + ")";
    }
}
