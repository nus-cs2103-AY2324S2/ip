import java.time.LocalDate;

public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = DateChecker.parseDate(from);
        this.to = DateChecker.parseDate(to);
    }

    @Override
    public String saveData() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateChecker.formatDate(this.from) + " to: " + DateChecker.formatDate(this.to) + ")";
    }
}
