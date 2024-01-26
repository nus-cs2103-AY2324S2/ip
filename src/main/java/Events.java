import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.TaskStatus;

class Events extends Task {

    private LocalDate from;
    private LocalDate to;

    public Events(String description, TaskStatus isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    public void setFrom(LocalDate newFrom) {
        this.from = newFrom;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}