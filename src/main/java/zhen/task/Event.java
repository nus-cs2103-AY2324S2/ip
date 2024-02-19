package zhen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private String startDateString;
    private String endDateString;
    public Event(String message, LocalDate startDate, LocalDate endDate) {
        super(message);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Event(String message, String startDate, String endDate) {
        super(message);
        this.startDateString = startDate;
        this.endDateString = endDate;
    }

    @Override
    public String toString() {
        String msg;
        if (isCompleted()) {
            msg = "[E][X] " + getMessage();
        } else {
            msg = "[E][ ] " + getMessage();
        }
        if (startDate != null && endDate != null) {
            return msg + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + tag;
        } else {
            return msg + " (from: " + startDateString + " to: " + endDateString + ")" + tag;
        }
    }
}
