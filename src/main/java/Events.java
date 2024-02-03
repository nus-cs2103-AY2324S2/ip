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
            this.fromDate = parseDate(from, f1, f2, f3, f4); // Parse the from string
            this.toDate = parseDate(to, f1, f2, f3, f4); // Parse the to string
        } catch (DateTimeException e) {
            throw new DateTimeException("Error parsing date in Events: " + e.getMessage());
        }
    }

    @Override
    protected String taskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return super.toString() + " (from: " + fromDate.format(displayFormatter) + ", to: " + toDate.format(displayFormatter) + ")";
    }
}
