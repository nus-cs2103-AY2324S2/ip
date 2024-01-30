import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    protected DateTimeFormatter formatter;
    public DateTimeParser() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }
    public LocalDateTime parseDateTime(String timeString) {
        return LocalDateTime.parse(timeString, this.formatter);
    }
}
