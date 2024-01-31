import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String desc, LocalDate from, LocalDate to) {
        super(isDone, desc);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s, to: %s)",
                this.getStatusIcon(),
                this.description,
                this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
