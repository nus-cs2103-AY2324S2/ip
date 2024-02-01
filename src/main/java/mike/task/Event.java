package mike.task;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final String TYPE = "Event";
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Event(String description, String startDate, String endDate) throws MikeException {
        super(description, TYPE);
        try {
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            throw new MikeException("Please enter a valid date in YYYY-MM-DD format.");
        }
        tags.add(ListViewType.DATE);
    }

    @Override
    public boolean in(ListView listView) {
        return super.in(listView) && listView.dateFilter(startDate);
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
        return "[E]" + super.toString() + " (from:" + getStartDate() + " to:" +  getEndDate() + ")";
    }
}
