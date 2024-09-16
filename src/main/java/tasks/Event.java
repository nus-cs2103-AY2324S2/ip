package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * For the creation of an Event class
 */
public class Event extends Task {

    protected LocalDate dateStart;
    protected LocalDate dateEnd;

    /**
     * Constructor for the Event class
     * @param name Name of the task
     * @param dateStart Starting time of the event
     * @param dateEnd Ending time of the event
     */
    public Event(String name, LocalDate dateStart, LocalDate dateEnd) {
        super(name);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[E]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (from: %s to: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name,
                this.dateStart.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                this.dateEnd.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
    @Override
    public String storeFormat() {
        String completeFormat = isComplete ? "1" : "0";
        return String.format("%s | %s | %s | %s | %s", "D", completeFormat, name,
                dateStart.format(DateTimeFormatter.ISO_DATE), dateEnd.format(DateTimeFormatter.ISO_DATE));
    }
}
