import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate fromDate;
    protected String fromTime;
    protected LocalDate toDate;
    protected String toTime;

    public Event(String description, LocalDate fromDate, String fromTime, LocalDate toDate, String toTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        return "[E][" + status + "] " + super.toString() + " (from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + fromTime
                + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + toTime + ")";
    }

    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDate + " " + fromTime + " " + toDate + " " + toTime;
    }
}
