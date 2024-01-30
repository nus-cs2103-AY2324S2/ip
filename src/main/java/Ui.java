import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {
    private static DateTimeFormatter printDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm");

    public static String printTime(LocalDateTime localDateTime) {
        return localDateTime.format(printDateTimeFormatter);
    }
}
