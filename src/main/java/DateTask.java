import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTask {
    LocalDateTime localDateTime;
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("'Date:' d MM yyyy 'Time:' h:mm a");
    public DateTask(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    
}
