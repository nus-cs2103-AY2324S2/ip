package headcube;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Events extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Events(String description, String start, String end) {
        super(description);
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.start = LocalDateTime.parse(start, originalFormatter);
            this.end = LocalDateTime.parse(end, originalFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.start = LocalDateTime.parse(start, targetFormatter);
                this.end = LocalDateTime.parse(end, targetFormatter);
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date and time format. Setting 24 hours from now");
                this.start = LocalDateTime.now();
                this.end = this.start.plusHours(24);
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
