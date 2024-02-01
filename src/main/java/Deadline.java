import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate tempBy;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "MMM dd yyyy");
        tempBy = LocalDate.parse(by);
        this.by = tempBy.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveInput() {
        return "D | " + isDone + " | " + description + " | " + tempBy;
    }
}