package virtue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VirtueDateTime {
    LocalDateTime dateTime;

    public VirtueDateTime(String str) throws DateTimeParseException {
        dateTime = LocalDateTime.parse(str);
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String fileFormat() {
        return dateTime.toString();
    }
}
