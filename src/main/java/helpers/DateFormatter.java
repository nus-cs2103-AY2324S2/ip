package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    public static String formatDate(String unformattedDate) {
        String[] dateTime = unformattedDate.split(" ");
        try {
            LocalDate date = LocalDate.parse(dateTime[0]);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + dateTime[1];
        } catch (DateTimeParseException e) {
            return unformattedDate;
        }
    }
}
