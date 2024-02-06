import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by; // Use LocalDateTime to include time

    public Deadline(String description, String by, boolean isComplete) {
        super(description);
        this.isComplete = isComplete;

        // Define a formatter that matches the input string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            // Parse the input string to a LocalDateTime object
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date-time: " + e.getMessage());
            // Handle the error appropriately. For simplicity, this example prints an error message.
            // In a real application, you might set a default value or throw a custom exception.
        }
    }

    @Override
    public String toFileFormat() {
        // For saving to file, convert LocalDateTime back to string using the same format
        return String.format("D | %d | %s | %s", isComplete ? 1 : 0, description, by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Override
    public String toString() {
        // Adjust the toString() method if you want to display the date-time in a specific format to the user
        // This example uses the same format as toFileFormat() for consistency
        return "D | " + (isComplete ? 1 : 0) + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
