package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime endDate;
    public Deadline(String name, LocalDateTime endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endDate.format(Task.dtf));
    }
}
