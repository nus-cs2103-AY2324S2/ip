import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String toString() {
        return String.format(" E | %s | %s | from: %s to: %s",
                getStatusIcon(),
                description,
                startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}