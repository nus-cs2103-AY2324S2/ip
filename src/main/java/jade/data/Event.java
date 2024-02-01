package jade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, LocalDate startDate, LocalDate endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String dateFormatter(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean isSameDate(LocalDate date) {
        return (date.isBefore(endDate) && date.isAfter(startDate) || date.equals(startDate) || date.equals(endDate));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), dateFormatter(startDate), dateFormatter(endDate));
    }

    @Override
    public String taskFormatter() {
        return String.format("E | %s | %s | %s - %s\n", statusFormatter(), description, dateFormatter(startDate), dateFormatter(endDate));
    }
}
