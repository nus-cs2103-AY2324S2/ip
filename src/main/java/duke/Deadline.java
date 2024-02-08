package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends duke.Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String formattedDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public String simpleToString() {
        return "D " + super.simpleToString() + " | " + this.formattedDeadline();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedDeadline() + ")";
    }
}
