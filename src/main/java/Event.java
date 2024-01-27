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

    @Override
    public String toString() {
        String from = this.startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String to = this.endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return String.format("[E]%s %s (from: %s to: %s)", (super.status ? "[X]" : "[ ]"), super.name, from, to);
    }
}
