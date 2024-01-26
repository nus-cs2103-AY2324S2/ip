package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.fromDate = LocalDateTime.parse(fromDate, formatter);
        this.toDate = LocalDateTime.parse(toDate, formatter);
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E | %s | %s | %s", fileLine.substring(5),
                this.fromDate.format(formatter), this.toDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.fromDate.format(formatter), this.toDate.format(formatter));
    }
}