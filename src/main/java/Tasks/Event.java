package Tasks;

import Parsing.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromWhen;
    protected LocalDateTime toWhen;
    protected DateTimeParser dateTimeParser;

    public Event(String name, String fromWhenString, String toWhenString) {
        super(name);
        this.dateTimeParser = new DateTimeParser();
        this.fromWhen = this.dateTimeParser.parseDateTime(fromWhenString);
        this.toWhen = this.dateTimeParser.parseDateTime(toWhenString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + toWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + fromWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) +
                " to " + toWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
