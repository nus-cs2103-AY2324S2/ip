package mike.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;

/**
 * An event, with a start date and end date.
 * @author ningc
 */
public class Event extends Task {
    private static final String TYPE = "Event";
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor.
     * @param description The event description.
     * @param startDate When the event starts.
     * @param endDate When the event ends.
     * @throws MikeException If the start date or end date cannot be parsed as a {@link LocalDate} object.
     */
    public Event(String description, String startDate, String endDate) throws MikeException {
        super(description, TYPE);
        try {
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            // todo: move this to parser?
            throw new MikeException("Please enter a valid date in YYYY-MM-DD format.");
        }
        tags.add(ListViewType.DATE);
    }

    @Override
    public boolean inListView(ListView listView) {
        return super.inListView(listView) && listView.dateFilter(startDate);
    }

    private String getStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String getEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String getFileEncoding() {
        return super.getFileEncoding() + "," + getStartDate() + "," + getEndDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + getStartDate() + " to:" + getEndDate() + ")";
    }
}
