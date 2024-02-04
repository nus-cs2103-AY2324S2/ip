package ben.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(boolean isDone, String description, LocalDate startDate, LocalDate endDate) {
        super(isDone, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + " | " + this.startDate + " | " + this.endDate;
    }

    @Override
    public String toString() {
        String formattedStartDate = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedStartDate + " to: " + formattedEndDate + ")";
    }
}
