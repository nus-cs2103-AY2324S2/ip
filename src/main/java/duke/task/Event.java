package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        String fromDateString = fromDate.format(dTFormatter);
        String toDateString = toDate.format(dTFormatter);
        return String.format("[E]" + super.toString() + " (from: %s to: %s)", fromDateString, toDateString);
    }
}
