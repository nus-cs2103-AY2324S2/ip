package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) throws DateTimeParseException {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorageFormat() {
        return "deadline | " + super.toStorageFormat() + " | " + this.by.format(Parser.INPUT_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Ui.OUTPUT_DATE_FORMATTER) + ")";
    }
}
