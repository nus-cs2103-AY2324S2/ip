import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        String startDate = this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String endDate = this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format(
                "[E][%s] %s (from: %s | to: %s)",
                this.getStatusIcon(),
                this.description,
                startDate,
                endDate
        );
    }

    public String toString2() {
        return String.format("E | %d | %s | %s | %s", this.isDone ? 1 : 0, this.description, this.start, this.end);
    }
}
