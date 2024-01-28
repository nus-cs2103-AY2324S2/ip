import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
    @Override
    public String toFileFormat() {
        return String.format("E %s %s %s", super.toFileFormat(), this.from, this.to);
    }
}
