import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Storage {
    public static String convertDateTimeForStorage(LocalDateTime localDateTime) {
        return CommandParser.convertDateTimeToCommandFormat(localDateTime);
    }
}
