import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    LocalDateTime date;
    String dateStr;
    public Date(String date) {
        this.dateStr = date;
        this.date = LocalDateTime.parse(date.trim());
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
    public String toSaveFormat() {
        return dateStr;
    }
}
