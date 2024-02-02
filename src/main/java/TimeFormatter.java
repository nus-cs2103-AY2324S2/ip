import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class TimeFormatter {
    public static LocalDate stringToTime(String s) {
        LocalDate ld = LocalDate.parse("s");
        String pattern = "yyyy-mm-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return ld;
    }
}
