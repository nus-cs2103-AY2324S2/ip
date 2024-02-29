package homie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that extends Task class. Handles all event related tasks.
 * Can specify event starting date time and ending date time.
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructor for Event class
     *
     * @param description The description of the event task.
     * @param start The start date time of event task in String, of 'dd MM yyyy HHmm' format
     * @param end The end date time of event task in String, of 'dd MM yyyy HHmm' format
     */
    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
        this.startDateTime = LocalDateTime.parse(start, formatter);
        this.endDateTime = LocalDateTime.parse(end, formatter);
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + super.toString() + " (from: "
            + this.startDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")) + " to: "
                + this.endDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")) + ")";
    }
}
