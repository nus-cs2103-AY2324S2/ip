import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTask {
    LocalDateTime localDateTime;
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("'Date:' d MM yyyy 'Time:' h:mm a");
    DateTimeFormatter DATE_TIME_FORMATTER_SAVE_AND_READ = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public DateTask(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public DateTask(String dateString) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER_SAVE_AND_READ);
        this.localDateTime = parsedDateTime;
    }

    public String saveFormat() {
        return localDateTime.format(DATE_TIME_FORMATTER_SAVE_AND_READ);
    }

    @Override
    public String toString() {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    
}
