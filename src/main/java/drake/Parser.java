package drake;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static int parseTaskIndex(String input) throws NumberFormatException {
        String[] words = input.split(" ", 2);
        if (words.length < 2) {
            throw new IllegalArgumentException("No task index provided.");
        }
        return Integer.parseInt(words[1]) - 1; // Subtract 1 to convert to zero-based index
    }

    public static String parseDescription(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TodoLeftBlank("Looks like you left the description of the todo empty. This isn't allowed!");
        }
        return parts[1].trim();
    }

    public static Object[] parseDeadline(String input) throws Exception {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Deadline time not provided.");
        }

        if (!parts[0].split(" ")[0].equals("deadline")) {
            throw new Exception("Looks like you spelt deadline wrong. Please try again!");
        }

        String description = parts[0].substring(parts[0].indexOf(' ') + 1).trim();
        LocalDate date;
        LocalDateTime by;

        try {
            date = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            by = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new Exception("Date is of the wrong format!");
        } 
        
        return new Object[]{description, by};
        
    }

    public static String[] parseEvent(String input) {
        String[] parts = input.substring(6).split("/");
        String title = parts[0];
        String from = "";
        String to = "";
    
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].split(" ")[0].equals("from")) {
                from = parts[i].substring(5);
            }
            if (parts[i].split(" ")[0].equals("to")) {
                to = parts[i].substring(3);
            }
        }
        return new String[]{title, from, to};
    }

}
