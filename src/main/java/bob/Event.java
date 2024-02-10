package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws DateTimeParseException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageFormat() {
        return "event | " + super.toStorageFormat() + " | " + this.from.format(Parser.INPUT_DATE_FORMATTER)
                + " | " + this.to.format(Ui.OUTPUT_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Ui.OUTPUT_DATE_FORMATTER)
                + " to: " + this.to.format(Ui.OUTPUT_DATE_FORMATTER) + ')';
    }
}
