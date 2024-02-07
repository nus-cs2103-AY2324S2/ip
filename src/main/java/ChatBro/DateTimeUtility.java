package ChatBro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class that contains method for parsing user input for date and time
 */
public class DateTimeUtility {
    public static String parseDateTime(String input) throws InvalidDateTimeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        if (input.contains(" ")) {
            try { // ddmmyyyy HHmm
                LocalDateTime dateWithTime = LocalDateTime.parse(input, dateTimeFormatter);
                return dateWithTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("Hey bro, that's an invalid date and time format.");
            }
        } else {
            try { // ddmmyyyy
                LocalDate date = LocalDate.parse(input, dateFormatter);
                return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            } catch (DateTimeParseException e2) {
                throw new InvalidDateTimeException("Hey bro, that's an invalid date and time format.");
            }
        }
    }
}