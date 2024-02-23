package tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.startDate.format(Task.OUTPUT_DATE_FORMAT), this.endDate.format(Task.OUTPUT_DATE_FORMAT));
    }
}
