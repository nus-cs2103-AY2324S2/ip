package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Date formatter
 */
public class DukeDateFormater {
    /**
     * Parses String date into LocalDate object.
     * @param dateStr Date in String.
     * @return Date in LocalDate type.
     */
    public LocalDate stringToDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    /**
     * Parses LocalDate to String.
     * @param date Date in LocalDate type.
     * @return Date in String.
     */
    public String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
