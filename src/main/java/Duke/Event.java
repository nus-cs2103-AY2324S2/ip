package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            // System.err.println("Error parsing event dates: " + from + " and " + to + ". Please use the format 'yyyy-MM-dd HHmm'.");
            this.from = null; // Set to null in case of parsing error
            this.to = null;   // Set to null in case of parsing error
        }
    }


    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
    public Boolean hasValidDate() {
        return this.from != null && this.to != null;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
