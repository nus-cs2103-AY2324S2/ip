import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String getInitialDesc() { return description; }

    public boolean getStatus() { return isDone; }

    public void mark(boolean status) {
        isDone = status;
    }

    public String convertDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
    }

}
