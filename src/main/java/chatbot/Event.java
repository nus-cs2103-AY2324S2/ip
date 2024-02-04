package chatbot;
import java.time.LocalDateTime;
public class Event extends chatbot.Task {
    protected java.time.LocalDateTime startTime;
    protected java.time.LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        String formattedStartTime = startTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String formattedEndTime = endTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedStartTime, formattedEndTime);
    }
}
