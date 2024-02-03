import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        String time = by.substring(3);
        this.by = parseDateTime(time);
    }

    private LocalDateTime parseDateTime(String by) {
        // Try to parse in the desired format
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            // If parsing fails, try the alternative format
            DateTimeFormatter alternativeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(by, alternativeFormatter);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatDateTime(by) + ")";
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }
}
