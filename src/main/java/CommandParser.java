import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandParser {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy'T'HHmm");
    public static LocalDateTime parseDateAndTime(String input) {
        return LocalDateTime.parse(input, dateTimeFormatter);
    }

    public static String convertDateTimeToCommandFormat(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }
}
