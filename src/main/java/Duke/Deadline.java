package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            this.by = null;
        }
    }
    public boolean hasValidDate() {
        return by != null;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}