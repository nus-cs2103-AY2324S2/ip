package Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private String dateTime;
    private LocalDateTime dateTimeData;
    public DateTime(String dateTime) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = dateTime;
        this.dateTimeData = LocalDateTime.parse(dateTime, inputFormat);
    }

    public boolean isValid() {
        try {

        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String formatDate() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");
        return this.dateTimeData.format(outputFormat);
    }
}
