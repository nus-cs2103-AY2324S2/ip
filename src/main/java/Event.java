import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start; // start date/time
    private LocalDateTime end; // end date/time

    public Event(String description, boolean completed, LocalDateTime start, LocalDateTime end) {
        super(description, completed);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public String getStartString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.start.format(dateTimeFormat);
    }

    public String getEndString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.end.format(dateTimeFormat);
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public void setStart(LocalDateTime date) {
        this.start = date;
    }

    public void setEnd(LocalDateTime date) {
        this.end = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getStartString() + " to: " + this.getEndString() + ")";
    }
}
