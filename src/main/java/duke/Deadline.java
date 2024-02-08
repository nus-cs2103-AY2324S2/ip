package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a string description and date and time in LocalDateTime format.
 */
public class Deadline extends duke.Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string in the specified format.
     * @return String in the format of "MMM dd yyyy HH:mm"
     */
    public String formattedDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns a simplified toString for the ease of saving.
     * @return String
     */
    public String simpleToString() {
        return "D " + super.simpleToString() + " | " + this.formattedDeadline();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedDeadline() + ")";
    }
}
