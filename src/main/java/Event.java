import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm][yyyy/MM/dd HH:mm][yyyy MM dd HH:mm][yyyy.MM.dd HH:mm]");
    private LocalDateTime start;
    private LocalDateTime end;

    public Event (String description, String start, String end) {
        super(description);
        if ((start.length() < 10 || (start.length() > 16 && start.length() != 64)) ||
            (end.length() < 10 || (end.length() > 16 && end.length() != 64))) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (start.length() == 10) {
            start += " 00:00";
        }
        if (end.length() == 10) {
            end += " 00:00";
        }
        this.start = LocalDateTime.parse(start, this.formatter);
        this.end = LocalDateTime.parse(end, this.formatter);
    }

    public Event (String description, String start, String end, boolean isDone) {
        super(description, isDone);
        if ((start.length() < 10 || (start.length() > 16 && start.length() != 64)) ||
            (end.length() < 10 || (end.length() > 16 && end.length() != 64))) {
            throw new IllegalArgumentException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
        if (start.length() == 10) {
            start += " 00:00";
        }
        if (end.length() == 10) {
            end += " 00:00";
        }
        this.start = LocalDateTime.parse(start, this.formatter);
        this.end = LocalDateTime.parse(end, this.formatter);
    }

    public String getStart() {
        return this.start.format(this.formatter);
    }

    public String getEnd() {
        return this.end.format(this.formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() 
            + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
            + " to " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}