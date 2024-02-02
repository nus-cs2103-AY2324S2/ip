import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class TimeFormatter {
    public static LocalDate stringToTime(String s) {
        String pattern = "yyyy-mm-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate ld = LocalDate.parse(s);
        return ld;
    }

    public static LocalDate loadTimeFromString(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate ld = LocalDate.parse(s, formatter);
        return ld;
    }
}
