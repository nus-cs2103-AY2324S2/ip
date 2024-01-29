import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Events extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Events(String description, String start, String end) {
        super(description);
        try {
            this.start = LocalDateTime.parse(start,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.end = LocalDateTime.parse(end,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time format. Setting 24 hours from now");
            this.start = LocalDateTime.now();
            this.end = this.start.plusHours(24);

        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
