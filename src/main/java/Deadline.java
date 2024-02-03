import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    protected LocalDate by;

    public Deadline (String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.DATE_TIME_FORMATTER) + ")";
    }
}
