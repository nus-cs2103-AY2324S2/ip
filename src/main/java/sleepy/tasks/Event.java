package sleepy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class Event extends Task {
    private String start;
    private String end;

    private LocalDate formattedFromDate;

    private LocalDate formattedToDate;

    /**
     * Constructor for the Event class.
     *
     * @param rawDescription Full description for the Event, including labels and timings.
     * @param description Description of the event details.
     * @param start When the event starts.
     * @param end When the event ends.
     */

    public Event(String rawDescription, String description, String start, String end) {
        super(rawDescription, description);
        this.start = start;
        this.end = end;
        try {
            formattedFromDate = LocalDate.parse(start);
        } catch (DateTimeParseException d) {
            formattedFromDate = null;
        }
        try {
            this.formattedToDate = LocalDate.parse(end);
        } catch (DateTimeParseException d) {
            formattedToDate = null;
        }
    }

    /**
     * Returns the description of this event.
     *
     * @return Description of this event.
     */
    @Override
    public String getDescription() {
        String startDate = formattedFromDate == null ?
                start : formattedFromDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));;
        String endDate = formattedToDate == null ?
                end : formattedToDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));;
        return "[E]" + super.getDescription() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
