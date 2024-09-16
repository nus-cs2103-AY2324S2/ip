package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, LocalDateTime start, LocalDateTime end, boolean isDone) {
        this(name, start, end);
        this.isDone = isDone;
    }

    @Override
    public String fileRepresentation() {
        return String.format("E | %s | %s | from: %s to: %s",
                this.getStatusIcon(),
                this.getDescription(),
                this.start.format(Task.formatter),
                this.end.format(Task.formatter));
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.start.format(Task.formatter),
                this.end.format(Task.formatter));
    }
}
