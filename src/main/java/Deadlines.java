import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.TaskStatus;

class Deadlines extends Task {
    private LocalDate byDate;

    public Deadlines(String description, TaskStatus isDone, LocalDate byDate) {
        super(description, isDone);
        this.byDate = byDate;
    }

    public LocalDate getByDate() {
        return this.byDate;
    }

    public void setByDate(LocalDate newByDate) {
        this.byDate = newByDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}