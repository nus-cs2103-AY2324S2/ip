package catchat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events class handles the events tasks of the application
 */
public class Events extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected String fromString;
    protected String toString;

    /**
     * Constructor for Events
     *
     * @param description
     * @param from
     * @param to
     * @throws DateTimeException
     */
    public Events(String description, String from, String to) {
        super(description);
        this.fromString = from;
        this.toString = to;
        try {
            this.fromDate = parseDate(from, DATE_FORMAT_1, DATE_FORMAT_2,
                    DATE_FORMAT_3, DATE_FORMAT_4); // Parse the "from" string
            this.toDate = parseDate(
                    to, DATE_FORMAT_1, DATE_FORMAT_2,
                    DATE_FORMAT_3, DATE_FORMAT_4); // Parse the "to" string
        } catch (DateTimeException e) {
            throw new DateTimeException("Error parsing date in Events: " + e.getMessage());
        }
    }

    @Override
    protected String getTaskTypeString() {
        return "E";
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DATE_FORMAT_4;
        return super.toString() + " (from: " + fromDate.format(displayFormatter) + ", to: "
                + toDate.format(displayFormatter) + ")";
    }
}
