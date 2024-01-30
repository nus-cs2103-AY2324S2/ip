import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime date;

    Deadline(String desc, String datestr) {
        super(desc);
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("MMM d'th' ha"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd ha"));
        DateTimeFormatter formatter = builder.toFormatter(Locale.US);
        if (!datestr.toLowerCase().contains("am") && !datestr.contains("pm")) {
            datestr += " 12am"; // append default time if none is provided
        }
        this.date = LocalDateTime.parse(datestr, formatter);
    }

    private String dateString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateString() + ")";
    }

    public String saveString() {
        return "D | " + super.saveString() + " | " + dateString();
    }
}
