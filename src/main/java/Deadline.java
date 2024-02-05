import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDate;
    protected boolean hasByDate;

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy HHmm");

    protected DateTimeFormatter dateTimeStringFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu ha");

    public Deadline(String description, String by) {
        super(description);
        try {
            this.byDate = LocalDateTime.parse(by, dateTimeFormatter);
            hasByDate = true;
        } catch (DateTimeParseException e) {
            this.by = by;
            hasByDate = false;
        }
    }

    @Override
    public String toString() {
        if (hasByDate) {
            return "[D]" + super.toString() + " (by: " + dateTimeStringFormatter.format(byDate) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}
