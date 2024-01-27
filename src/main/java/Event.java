import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final String symbol = "[E]";
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super("[E]", description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformatter
                = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return this.symbol + super.toString() + " (From: " + dateformatter.format(this.start)
                + " To: " + dateformatter.format(this.end) + ")";
    }
}
