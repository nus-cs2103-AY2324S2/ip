package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use dd/MM/yyyy");
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(dateFormat);
    }
}
