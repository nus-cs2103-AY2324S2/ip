package duke;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events class
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
            this.fromDate = parseDate(from, dateFormat1, dateFormat2, dateFormat3, dateFormat4); // Parse the from string
            this.toDate = parseDate(to, dateFormat1, dateFormat2, dateFormat3, dateFormat4); // Parse the to string
        } catch (DateTimeException e) {
            throw new DateTimeException("Error parsing date in Events: " + e.getMessage());
        }
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = dateFormat4;
        return super.toString() + " (from: " + fromDate.format(displayFormatter) + ", to: " + toDate.format(displayFormatter) + ")";
    }
}
