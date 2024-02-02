import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String by1 = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "D" + super.toString() + " | by: " + by1;
    }
}
