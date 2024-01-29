import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    private String description;
    private boolean isDone;
    private LocalDateTime deadline;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Constructor with deadline
    public Task(String description, LocalDateTime deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        String deadlineString = (deadline != null) ? " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")" : "";
        return status + " " + getDescription() + deadlineString;
    }
}
