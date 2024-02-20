package Duke.Commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    public static LocalDate format(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public static boolean compareDate(LocalDate d1, LocalDate d2) {
        return d2.isAfter(d1);
    }

    public static String reformatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }
}
