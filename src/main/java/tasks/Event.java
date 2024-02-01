package tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate.toString();
    }

    public String getStartDate() {
        return startDate.toString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.startDate.format(Task.dtf), this.endDate.format(Task.dtf));
    }
}
