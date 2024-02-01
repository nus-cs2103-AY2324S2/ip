package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * For the creation of an Event class
 */
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for the Event class
     * @param name Name of the task
     * @param start Starting time of the event
     * @param end Ending time of the event
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[E]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (from: %s to: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name,
                this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
    @Override
    public String storeFormat() {
        String completeFormat = complete ? "1" : "0";
        return String.format("%s | %s | %s | %s | %s", "D", completeFormat, name,
                start.format(DateTimeFormatter.ISO_DATE), end.format(DateTimeFormatter.ISO_DATE));
    }
}
