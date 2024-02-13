package dav;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EventTask extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public EventTask(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public EventTask(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " | " + toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 5) {
            String[] dateTimeParts = parts[3].split(" ");
            String[] dateTimePartsTo = parts[4].split(" ");
            if (dateTimeParts.length == 2 && dateTimePartsTo.length == 2) {
                EventTask eventTask = new EventTask(parts[2], dateTimeParts[0] + " " + dateTimeParts[1],
                                                    dateTimePartsTo[0] + " " + dateTimePartsTo[1]);
                eventTask.isDone = parts[1].equals("1");
                return eventTask;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

