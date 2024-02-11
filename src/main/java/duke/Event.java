package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, String start, String end) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.start = LocalDateTime.parse(start, formatter);
            this.end = LocalDateTime.parse(end, formatter);
        } catch (DateTimeParseException e) {
            // Handle exception or set a default value if the date-time is in the wrong format
            throw new DukeException("Invalid datetime format (yyyy-mm-dd hh:mm).");
        }
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public String getStartForFile() {
        return start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getEndForFile() {
        return end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }
}
