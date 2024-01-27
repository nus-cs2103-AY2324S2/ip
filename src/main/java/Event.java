import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone, TaskType.EVENT);
        this.startTime = LocalDateTime.parse(startTime, super.dateTimeEntryForm);
        this.endTime = LocalDateTime.parse(endTime, super.dateTimeEntryForm);
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String getSaveFileString() {
        return String.format("E | %d | %s | %s | %s", super.getDoneAsInt(),
                super.getDescription(), this.getStartTime().format(super.dateTimeEntryForm),
                this.getEndTime().format(dateTimeEntryForm));
    }
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.getStartTime().format(dateTimeOutForm),
                this.getEndTime().format(dateTimeOutForm));
    }
}
