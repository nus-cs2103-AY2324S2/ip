package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate){
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(outputFormatter) + " to: " +
                this.toDate.format(outputFormatter) +")";
    }
}
