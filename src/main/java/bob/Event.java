package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDateTime.parse(from, Parser.INPUT_DATE_FORMATTER);
        this.to = LocalDateTime.parse(to, Parser.INPUT_DATE_FORMATTER);
    }

    public String format() {
        return "event | " + super.format() + " | " + this.from.format(Parser.INPUT_DATE_FORMATTER)
                + " | " + this.to.format(Ui.OUTPUT_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Ui.OUTPUT_DATE_FORMATTER)
                + " to: " + this.to.format(Ui.OUTPUT_DATE_FORMATTER) + ')';
    }
}
