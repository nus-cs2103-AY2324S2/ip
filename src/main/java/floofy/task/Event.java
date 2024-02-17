package floofy.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate eventFromDate, LocalDate eventToDate) {
        super(description);
        this.from = eventFromDate;
        this.to = eventToDate;
    }

    public String getDateString(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + String.format(" | %s - %s", getDateString(this.from), getDateString(this.to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
