package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    // code snippet below inspired from:
    // https://stackoverflow.com/questions/44600420/datetimeformatter-accepting-multiple-dates-and-converting-to-one-java-time-libr
    protected DateTimeFormatter parser = DateTimeFormatter.ofPattern("[yyyy-MM-dd HHmm][MMM dd yyyy HHmm]");

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDateTime = LocalDateTime.parse(start, parser);
        this.endDateTime = LocalDateTime.parse(end, parser);
    }

    public Event(String status, String description, String start, String end) {
        super(status.equals("1"), description);
        this.start = start;
        this.end = end;
        this.startDateTime = LocalDateTime.parse(start, parser);
        this.endDateTime = LocalDateTime.parse(end, parser);
    }

    public String startDateTimeFormat() {
        return this.startDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public String endDateTimeFormat() {
        return this.endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + this.startDateTimeFormat() + " - " + this.endDateTimeFormat();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startDateTimeFormat()
                + " to: " + this.endDateTimeFormat() + ")";
    }
}
