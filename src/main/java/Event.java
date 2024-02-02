import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDate.parse(from, Parser.INPUT_DATE_FORMATTER);
        this.to = LocalDate.parse(to, Parser.INPUT_DATE_FORMATTER);
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
