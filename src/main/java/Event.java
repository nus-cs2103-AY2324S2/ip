import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        //return " E | " + getStatusIcon() + " | " + super.toString() + " | from: " + this.from + " | to: " + this.to;
        return String.format(" E | %s | %s (from: %s to: %s)",
                isDone ? "X" : " ",
                description,
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}