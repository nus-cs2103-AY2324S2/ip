package chipchat.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Event(String description, boolean isDone, LocalDate dateFrom, LocalDate dateTo) {
        super(description, isDone);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.dateFrom, this.dateTo);
    }
}
