import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event - Represents an event task with start and end dates, a subclass of Task.
 */
public class Event extends Task {
    private String fromDate;
    private String toDate;
    private LocalDate d1;
    private LocalDate d2;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(String description, LocalDate d1, LocalDate d2) {
       super(description);
       this.d1 = d1;
       this.d2 = d2;
    }

    @Override
    public String toString() {
        if (d1 != null && d2 != null) {
            return "[E]" + super.toString() + " (from: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                    + d2.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
