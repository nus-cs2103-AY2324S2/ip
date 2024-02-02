package ukecat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String isDone, String description, LocalDate start, LocalDate end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start.toString();
    }

    public String getEnd() {
        return this.end.toString();
    }

    @Override
    public String toString() {
        String info = String.format("(from: %s to: %s)",
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return String.format("[E]%s%s %s", this.getStatusIcon(), super.toString(), info);
    }
}
