package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles events that have start and end date/time
 */
public class Event extends Task {
    /**
     * The start date/time of the task
     */
    LocalDateTime startDate;

    /**
     * The end date/time of the task
     */
    LocalDateTime endDate;

    /**
     * Creates an Event object to handle an Event task
     *
     * @param name the name of the event
     * @param startDate the start date/time of the event
     * @param endDate the end date/time of the event
     * @param isDone whether the event is done
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[E]%s (from: %s to: %s)", super.toString(),
                startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s event %s /from %s /to %s", isDone, name, startDate, endDate);
        return str;
    }
}
