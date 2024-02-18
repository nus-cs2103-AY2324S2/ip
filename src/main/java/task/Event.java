package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents an event task with a specified description and duration.
 */
public class Event extends Task {
    private final String symbol = "[E]";

    public Event(String description, LocalDate start, LocalDate end) {
        super("[E]", description, start, end);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return this.symbol + super.toString() + " (From: " + dateformatter.format(this.start)
                + " To: " + dateformatter.format(this.end) + ")";
    }
}
