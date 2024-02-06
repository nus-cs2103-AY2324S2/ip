import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by, boolean isComplete) {
        super(description);
        this.isComplete = isComplete;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date-time: " + e.getMessage());
        }
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", isComplete ? 1 : 0, description, by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Override
    public String toString() {
        return "D | " + (isComplete ? 1 : 0) + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
