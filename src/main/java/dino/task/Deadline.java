package dino.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadlineTime;

    public Deadline(String description, LocalDateTime deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public LocalDateTime getDateTime() {
        return deadlineTime;
    }

    public String toString() {
        return String.format(" D | %s | %s | by: %s",
                getStatusIcon(),
                description,
                deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}

