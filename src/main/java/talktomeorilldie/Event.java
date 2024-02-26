package talktomeorilldie;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private final LocalTime fromTime;
    private final LocalTime to;
    private final String fromDate;

    /**
     * Constructor for Event.
     * @param description Description of the event.
     * @param fromDate Starting date of the event.
     * @param fromTime Starting date and time of the event.
     * @param to Ending date and time of the event.
     */
    public Event(String description, String fromDate, LocalTime fromTime, LocalTime to) {
        super(description);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.to = to;
    }

    /**
     * Returns the starting date and time of the event.
     * @return Starting date and time of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " "
                + fromTime.format(DateTimeFormatter.ofPattern("h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }

    /**
     * Returns the string representation of the event for saving to file.
     * @return String representation of the event for saving to file.
     */
    @Override
    public String toSaveString() {
        return "E | " + (isDone ? "1" : "0") + " | "
                + description + " | " + fromDate.replaceAll(":", "") + " " + fromTime + " - " + to;
    }
}
