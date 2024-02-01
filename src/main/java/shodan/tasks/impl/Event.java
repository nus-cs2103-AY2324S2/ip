package shodan.tasks.impl;

import java.time.LocalDateTime;

public class Event extends shodan.tasks.Task {
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
                super.toString(), this.startDate.format(shodan.tasks.Task.dtf), this.endDate.format(shodan.tasks.Task.dtf));
    }
}
