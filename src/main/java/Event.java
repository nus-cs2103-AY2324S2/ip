import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public static final String TYPE_SYMBOL = "E";
    private final LocalDateTime begin;
    private final LocalDateTime end;
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String description, LocalDateTime begin, LocalDateTime end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    public Event(String description, boolean isDone, LocalDateTime begin, LocalDateTime end) {
        super(description, isDone);
        this.begin = begin;
        this.end = end;
    }

    private String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + "," + formatDate(this.begin) + "," + formatDate(this.end);
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString() + " (from: " + formatDate(this.begin) + " to: " + formatDate(this.end) + ")";
    }
}
