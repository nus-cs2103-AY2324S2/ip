import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String[] dateTimeFormats = {
                "yyyy-MM-dd HH:mm", "dd/MM/yyyy HH:mm", "MM/dd/yyyy HH:mm",
                "yyyy-MM-dd HHmm", "dd/MM/yyyy HHmm", "MM/dd/yyyy HHmm",
                "dd-MM-yyyy HH:mm", "dd-MM-yyyy HHmm", "d-MM-yyyy HH:mm", "d-MM-yyyy HHmm",
                "dd-M-yyyy HH:mm", "dd-M-yyyy HHmm", "d-M-yyyy HH:mm", "d-M-yyyy HHmm",
                "d/M/yyyy HH:mm", "d/M/yyyy HHmm", "dd/M/yyyy HH:mm", "dd/M/yyyy HHmm"
        };

        try {
            return LocalDateTime.parse(dateTimeString, isoFormatter);
        } catch (DateTimeParseException e) {
            // Ignore and try other formats
        }

        for (String format : dateTimeFormats) {
            try {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        throw new DukeException("Invalid date-time format. Please use a valid format such as yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.");
    }
}
