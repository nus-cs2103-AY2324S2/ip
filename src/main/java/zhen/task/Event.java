package zhen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    public Event(String message, LocalDate startDate, LocalDate endDate) {
        super(message);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String msg;
        if (isCompleted()) {
            msg = "[E][X] " + getMessage();
        } else {
            msg = "[E][ ] " + getMessage();
        }
        return msg + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
