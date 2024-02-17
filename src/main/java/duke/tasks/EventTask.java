package duke.tasks;

import java.time.LocalDate;

public class EventTask extends Task {
    protected LocalDate startBy;
    protected LocalDate endBy;
    public EventTask(String description, LocalDate startBy, LocalDate endBy) {
        super(description);
        this.startBy = startBy;
        this.endBy = endBy;
    }

    public LocalDate getStartBy() {
        return this.startBy;
    }
    public LocalDate getEndBy() {
        return this.endBy;
    }
    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " | " + "(from: " + getStartBy() + " to: " + getEndBy() + ")";
    }
}
