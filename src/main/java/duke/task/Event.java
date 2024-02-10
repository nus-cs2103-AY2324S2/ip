package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.E);
        this.start = start;
        this.end = end;
    }

    @Override
    public String storageString() {
        // Format the task status, task information, start, and end into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[E] | %s | %s | %s | %s", isCompleted, this.getDescription().trim(),
                this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
