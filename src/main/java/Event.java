import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) throws DukeException {
        super(description);
        try {
            this.from = from;
            this.to = to;
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify the correct format date: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String getDataString() {
        return "E | " + (isDone? "1" : "0") + " | " + super.toString() + " | " + from + " | " + to;
    }
}
