package Tasks;

import Parsing.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byWhen;
    protected DateTimeParser dateTimeParser;

    public Deadline(String name, String byWhenString) {
        super(name);
        this.dateTimeParser = new DateTimeParser();
        this.byWhen = this.dateTimeParser.parseDateTime(byWhenString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + byWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
