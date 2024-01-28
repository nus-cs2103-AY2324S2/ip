package tasks;

import java.time.LocalDateTime;

public class Parser {

    public static LocalDateTime parseDate(String date) {
        String[] dateTimeSplit = date.split("T");
        String dateString = dateTimeSplit[0];
        String timeString = dateTimeSplit[1];
        String[] parts = dateString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        String[] timeParts = timeString.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return LocalDateTime.of(year,month,day, hour, minutes);
    }
}
