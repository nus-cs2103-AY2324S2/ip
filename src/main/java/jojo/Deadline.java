package jojo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a string description and date and time in LocalDateTime format.
 */
public class Deadline extends Task {

    protected LocalDateTime byWhen;

    public Deadline(String description, LocalDateTime byWhen) {
        super(description);
        this.byWhen = byWhen;
    }

    /**
     * Returns a string in the specified format.
     * @return String in the format of "MMM dd yyyy HH:mm"
     */
    public String formattedDeadline() {
        return byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns a simplified toString for the ease of saving.
     * @return String
     */
    public String simpleToString() {
        return "D " + super.simpleToString() + " | " + formattedDeadline();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDeadline() + ")";
    }
}
