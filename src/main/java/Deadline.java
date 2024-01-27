import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime endTime;
    public Deadline(String description, boolean isDone, String endTime) {
        super(description, isDone, TaskType.DEADLINE);
        this.endTime = LocalDateTime.parse(endTime, super.dateTimeEntryForm);
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String getSaveFileString() {
        return String.format("D | %d | %s | %s", super.getDoneAsInt(),
                super.getDescription(), this.getEndTime());
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                this.getEndTime().format(super.dateTimeOutForm));
    }
}
