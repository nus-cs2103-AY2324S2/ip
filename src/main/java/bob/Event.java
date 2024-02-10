package bob;

import java.time.LocalDate;
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
        return "event | " + super.toStorageFormat() + " | " + this.from.format(Parser.INPUT_DATETIME_FORMATTER)
                + " | " + this.to.format(Parser.INPUT_DATETIME_FORMATTER);
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        return !(date.isBefore(from.toLocalDate()) || date.isAfter(to.toLocalDate()));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Ui.OUTPUT_DATETIME_FORMATTER)
                + " to: " + this.to.format(Ui.OUTPUT_DATETIME_FORMATTER) + ')';
    }
}
