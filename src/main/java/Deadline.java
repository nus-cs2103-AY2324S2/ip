import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy | hh:mm a")) + ")";
    }

    @Override
    public String toStringForFile() {
        return "D" + super.toStringForFile() + " | " + this.deadline;
    }
}
