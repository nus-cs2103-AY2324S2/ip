package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.E);
        this.from = from;
        this.to = to;
    }

    @Override
    public String storageString() {
        // Format the task status, task information, from, and end into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[E] | %s | %s | %s | %s", isCompleted, this.getDescription().trim(),
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
