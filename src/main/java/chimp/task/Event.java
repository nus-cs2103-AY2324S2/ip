package chimp.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String text, TaskStatus status, LocalDate from, LocalDate to) {
        super(text, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " )";
    }
}
