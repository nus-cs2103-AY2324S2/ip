import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String listItem, String inputItem, LocalDateTime startTime, LocalDateTime endTime) {
        super(listItem, inputItem);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" +
                (this.taskDone ? "[X] " : "[ ] ") +
                this.listItem +
                " (from: " +
                this.startTime +
                " to: " +
                this.endTime +
                ")"
                ;
    }
}
