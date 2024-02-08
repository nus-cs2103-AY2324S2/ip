package liv.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements an event as a type of task.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;
    /**
     * The pattern of DateTime that is printed to the user.
     */
    private static final String OUTPUT_PATTERN = "MMM dd yyyy HH:mm";

    /**
     * The constructor of the class.
     * @param description Desription of the event.
     * @param from Starting time of the event.
     * @param to End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     * Uses [E] to denote event type.
     * Prints the start and end time of the event along with the description.
     */
    @Override
    public String getDisplayedString() {
        return "[E]" + getStatusIcon() + " " + getDescription() + " (from: "
                + from.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN)) + " to: "
                + to.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN)) + ")";
    }

    /**
     * {@inheritDoc}
     * Uses [E] to denote event type.
     * Stores the start and end time of the event along with the description.
     */
    @Override
    public String serializeTask() {
        return "[E] | " + getStatusIcon() + " | " + getDescription() + " | " + from + " " + to;
    }

}
