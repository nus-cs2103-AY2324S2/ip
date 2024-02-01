package shodan.tasks.impl;

import java.time.LocalDateTime;

public class Deadline extends shodan.tasks.Task {
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
        return String.format("[D]%s (by: %s)", super.toString(), this.endDate.format(shodan.tasks.Task.dtf));
    }
}
