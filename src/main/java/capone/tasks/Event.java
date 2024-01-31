package capone.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String fromDateTimeString;
    protected String toDateTimeString;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;



    public Event(String description, boolean status, String fromDate, String toDate) {
        super(TaskType.EVENT, description, status);
        this.fromDateTimeString = fromDate;
        this.toDateTimeString = toDate;
    }

    public Event(String description, boolean status, LocalDateTime fromDate, LocalDateTime toDate) {
        super(TaskType.EVENT, description, status);
        this.fromDateTime = fromDate;
        this.toDateTime = toDate;
    }

    public String getFromDateTime() {
        if (this.fromDateTime != null) {
            return this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return this.fromDateTimeString;
    }

    public String getToDateTime() {
        if (this.toDateTime != null) {
            return this.toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return this.toDateTimeString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromDateTime()
                + " to: " + this.getToDateTime() + ")";
    }
}
