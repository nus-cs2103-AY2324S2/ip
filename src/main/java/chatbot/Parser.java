package chatbot;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public Parser() {
    }
    public String parseDescription(String input) {
        // Extract description from input
        return input.substring(input.indexOf(" ") + 1).trim();
    }
    public LocalDateTime parseDateTime(String input) {
        // Parse date-time from input
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }
}
