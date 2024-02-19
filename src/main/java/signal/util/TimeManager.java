package signal.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeManager {
    public TimeManager() {
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }

    public String formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }
}
