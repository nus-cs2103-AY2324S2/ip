import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String desc, String from, String to) {
        super(desc);
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("MMM d'th' ha"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"));
        DateTimeFormatter formatter = builder.toFormatter(Locale.US);
        if (!from.toLowerCase().contains("am") && !from.toLowerCase().contains("pm")) {
            from += " 12am"; // append default time if none is provided
        }
        if (!to.toLowerCase().contains("am") && !to.toLowerCase().contains("pm")) {
            to += " 12am"; // append default time if none is provided
        }
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    private String startString() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
    }

    private String stopString() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
    }

    public String toString() {
        String from = startString();
        String to = stopString();
        if (from.length() >= 12 && to.length() >= 12 && from.substring(0, 12).equals(to.substring(0, 12))) {
            to = to.substring(12);
        }
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String saveString() {
        return "E | " + super.saveString() + " | " + startString() + " | " + stopString();
    }
}
