import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateChecker {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_FORMAT);
    }
}
