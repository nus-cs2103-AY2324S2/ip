// Event.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        // Parse input dates with both date and time components
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        // Format dates in MMM dd yyyy hh:mma format
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));

        return "E |" + super.toString() + " |" + " from: " + formattedFrom + "  to: " + formattedTo ;
    }
}
