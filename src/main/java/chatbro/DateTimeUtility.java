package chatbro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class with method for parsing user input into a valid date and time format.
 */
public class DateTimeUtility {
    /**
     * Parses the user input into a valid date and time format.
     *
     * @param input User's input date and time.
     * @return Valid date and time format.
     * @throws InvalidDateTimeException If the input is not in a valid date and time format.
     */
    public static String parseDateTime(String input) throws InvalidDateTimeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        if (input.contains(" ")) {
            try { // input is: dd-mm-yyyy HHmm
                LocalDateTime dateWithTime = LocalDateTime.parse(input, dateTimeFormatter);
                return dateWithTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("Hey bro, that's an invalid date and time format.");
            }
        } else {
            try { // input is: dd-mm-yyyy
                LocalDate date = LocalDate.parse(input, dateFormatter);
                return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            } catch (DateTimeParseException e2) {
                throw new InvalidDateTimeException("Hey bro, that's an invalid date and time format.");
            }
        }
    }
}
