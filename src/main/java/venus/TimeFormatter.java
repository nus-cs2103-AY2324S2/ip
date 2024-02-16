package venus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a TaskList class that is used to save tasks.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class TimeFormatter {
    /**
     * Returns date in LocalDate class given input String of format "yyyy-mm-dd".
     * @param s date String in format of "yyyy-mm-dd".
     * @return LocalDate of input.
     * @throws DateTimeParseException Throws exception when invalid date format is used.
     */
    public static LocalDate stringToTime(String s) throws DateTimeParseException {
        String pattern = "yyyy-mm-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate ld = LocalDate.parse(s);
        return ld;
    }

    /**
     * Returns date in LocalDate class given input String of format "MMM d yyyy".
     * @param s date String in format of "MMM d yyyy".
     * @return LocalDate of input.
     * @throws DateTimeParseException Throws exception when invalid date format is used.
     */
    public static LocalDate loadTimeFromString(String s) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate ld = LocalDate.parse(s, formatter);
        return ld;
    }
}
