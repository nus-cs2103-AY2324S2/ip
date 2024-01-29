import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines - Represents a task with a deadline, a subclass of Task.
 */

public class Deadlines extends Task {
    private String byDate;
    private LocalDate d1;
    public Deadlines(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    public Deadlines(String description, LocalDate d1) {
        super(description);
        this.d1 = d1;
    }

    @Override
    public String toString() {
        if (d1 != null) {
            return "[D]" + super.toString() + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}