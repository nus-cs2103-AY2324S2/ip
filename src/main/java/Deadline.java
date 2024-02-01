import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String byString) {
        super(description);
        this.by = parseDeadline(byString);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    private LocalDateTime parseDeadline(String byString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(byString, formatter);
    }

    public String getFormattedDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
