package bob;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by, Parser.INPUT_DATE_FORMATTER);
    }

    public String format() {
        return "deadline | " + super.format() + " | " + this.by.format(Parser.INPUT_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Ui.OUTPUT_DATE_FORMATTER) + ")";
    }
}
