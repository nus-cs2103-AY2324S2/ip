import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    private LocalDateTime deadlineTime;

    public Deadline(String description, LocalDateTime deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public String toString() {
        //return " D | " + getStatusIcon() + " | " + super.toString() + " | by: " + this.by;
        return String.format(" D | %s | %s (by: %s)",
                isDone ? "X" : " ",
                description,
                deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));

    }
}

