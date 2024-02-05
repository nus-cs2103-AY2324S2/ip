package venus;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

public class TimeFormatter {
    public static LocalDate stringToTime(String s) throws DateTimeParseException {
        String pattern = "yyyy-mm-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate ld = LocalDate.parse(s);
        return ld;
    }

    public static LocalDate loadTimeFromString(String s) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate ld = LocalDate.parse(s, formatter);
        return ld;
    }
}
