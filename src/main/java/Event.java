import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, inputFormatter);
        this.end = LocalDateTime.parse(end, inputFormatter);
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s", this.getStatusIcon(), this.getDescription(),
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }
}
