package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + super.description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | " +  from + " - " + to;
    }

    public static Event fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("E")) {
            return null; // or throw an exception
        }
        String description = parts[2].trim();
        String[] times = parts[3].split(" - ");
        if (times.length < 2) {
            return null;
        }
        String from = times[0].trim();
        String to = times[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
        boolean isDone = parts[1].trim().equals("1");
        Event event = new Event(description, dateTimeFrom, dateTimeTo);
        if (isDone) event.markAsDone();
        return event;
    }

    public String getFromDate() {
        return from.toString();
    }

    public String getToDate() {
        return to.toString();
    }
}
