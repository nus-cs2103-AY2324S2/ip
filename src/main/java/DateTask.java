import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTask {
    LocalDateTime localDateTime;
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("'Date:' d MM yyyy 'Time:' h:mm a");
    DateTimeFormatter DATE_TIME_FORMATTER_SAVE = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public DateTask(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String saveFormat() {
        return localDateTime.format(DATE_TIME_FORMATTER_SAVE);
    }

    @Override
    public String toString() {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    
}
