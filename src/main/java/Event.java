import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean queryByDate(LocalDate date) {
        if ((date.isAfter(this.startDate) && date.isBefore(this.endDate)) || date.isEqual(this.startDate) || date.isEqual(this.endDate)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String from = this.startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String to = this.endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return String.format("[E]%s %s (from: %s to: %s)", (super.status ? "[X]" : "[ ]"), super.name, from, to);
    }
}
