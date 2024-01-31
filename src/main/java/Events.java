import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


class Events extends Task {
    private String start;
    private String end;
    private LocalDateTime actualStart;
    private LocalDateTime actualEnd;

    Events(String description, String start, String end, LocalDateTime actualStart, LocalDateTime actualEnd, int num) {
        super(description, num);
        this.start = start;
        this.end = end;
        this.actualStart = actualStart;
        this.actualEnd = actualEnd;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }
    @Override
    public String toString() {
        if (this.isMarked()) {
            return "[E][X] " + super.toString() + "(from: " + this.start.substring(5) + 
            " to: " + this.end.substring(3) + ")";
        } else {
            return "[E][ ] " + super.toString() + "(from: " + this.start.substring(5) + 
            " to: " + this.end.substring(3) + ")";
        }
    }
    @Override
    public String identifier() {
        return "[E]";
    }

    @Override
    public LocalDate getDeadline() {
        return null;
    }
}