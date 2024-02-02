package jade.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Event</code> object represents a user task with a start date and an end date.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Class constructor specifying the event description, start date, and end date.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Another Event constructor.
     * Specifying the deadline description, start date, end date, and the completion status.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Returns a formatted string of the LocalDate object.
     */
    public String dateFormatter(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * @inheritDoc This implementation checks whether the event dates include the date.
     */
    @Override
    public boolean isSameDate(LocalDate date) {
        return (date.isBefore(endDate) && date.isAfter(startDate) || date.equals(startDate) || date.equals(endDate));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), dateFormatter(startDate), dateFormatter(endDate));
    }

    /**
     * @inheritDoc Adds the start date and end date at the end.
     */
    @Override
    public String taskFormatter() {
        return String.format("E | %s | %s | %s - %s\n", statusFormatter(), description, dateFormatter(startDate), dateFormatter(endDate));
    }
}
