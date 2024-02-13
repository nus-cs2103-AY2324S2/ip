package paimon.task;
import java.time.LocalDateTime;
public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;

    }

    @Override
    public String getTask() {
        if (isDone) {
            return "[E][X] " + this.description + " (from: " + startDate.format(DATE_FORMAT) + ", to: " + this.endDate.format(DATE_FORMAT) + ")";
        } else {
            return "[E][ ] " + this.description + " (from: " + startDate.format(DATE_FORMAT) + ", to: " + this.endDate.format(DATE_FORMAT) + ")";
        }
    }
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "E | 1 | " + this.description + " | "  + this.startDate.format(DATE_FORMAT) + " | " + this.endDate.format(DATE_FORMAT);
        } else {
            return "E | 0 | " + this.description + " | "  + this.startDate.format(DATE_FORMAT) + " | " + this.endDate.format(DATE_FORMAT);
        }
    }
}
