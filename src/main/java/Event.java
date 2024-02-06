import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    Event(String text, TaskStatus status, LocalDate from, LocalDate to) {
        super(text, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + " )";
    }
}
