import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;

    protected LocalDate byDate;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;

        // Check if they are in dateTime format
        try {
            byDate = LocalDate.parse(by);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {}
    }

    @Override
    public String fileString() {
        return "D | " + super.fileString() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
