package Duke.activityAndUtility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class DateTimeFormat {
    static public LocalDate getDate(String input) {
        String[] dateString = input.split(" ", 2);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(dateString[0], formatter1);
        } catch (DateTimeParseException ignored) {
        }
        try {
            return LocalDate.parse(dateString[0], formatter2);
        } catch (DateTimeParseException format) {
            return null;
        }
    }

    static public LocalTime getTime(String input) {
        String[] timeString = input.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(timeString[1], formatter);
        } catch (DateTimeParseException format) {
            return null;
        }
    }
}
