package youdon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTimeStart = this.start.format(formatter);
        String formattedDateTimeEnd = this.end.format(formatter);
        return "[" + this.getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description + " (from: " + formattedDateTimeStart + " to: " + formattedDateTimeEnd + ")";
    }
}
