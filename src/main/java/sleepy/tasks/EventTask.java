package sleepy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class is a type of task.
 *
 * @author kjw142857
 */
public class EventTask extends Task {
    private String start;
    private String end;

    private LocalDate formattedFromDate;
    private LocalDate formattedToDate;

    /**
     * Constructor for the EventTask class.
     *
     * @param description Description of the event details.
     * @param start When the event starts.
     * @param end When the event ends.
     */

    public EventTask(String description, String start, String end) {
        super(description);
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
        String startDate = formattedFromDate == null
                ? start : formattedFromDate.format(DATE_FORMAT);
        String endDate = formattedToDate == null
                ? end : formattedToDate.format(DATE_FORMAT);
        return "[E]" + super.getDescription() + " (from: " + startDate + " to: " + endDate + ")";
    }

    /**
     * Returns the raw description of this event (as it was added by the user).
     *
     * @return Raw description of this event.
     */
    @Override
    public String getRawDescription() {
        String details = super.getDescription().substring(TASK_DESCRIPTION_OFFSET);
        return "event " + details + "/from " + start + "/to " + end;
    }
}
