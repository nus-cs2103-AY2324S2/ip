import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = TaskType.EVENT;
    }

    public LocalDate getFrom() {
        return from;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return type.getSymbol() + super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}