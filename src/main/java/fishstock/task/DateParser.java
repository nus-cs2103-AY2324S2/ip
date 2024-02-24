package fishstock.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates parsing methods for Dates.
 */
class DateParser {
    private static final DateTimeFormatter IN_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    private static final DateTimeFormatter OUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Parses date string into LocalDateTime object.
     * Has the format "[dd/mm/yyyy] [hh:mm]".
     *
     * @param date The date string.
     * @return The LocalDateTime object.
     * @throws TaskException The exceptions while parsing the date.
     */
    protected static LocalDateTime parseDate(String date) throws TaskException {
        try {
            return LocalDateTime.parse(date, IN_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new TaskException("OH NOSE! Dates should be of the format <dd/mm/yyyy hh:mm>");
        }
    }

    /**
     * Converts LocalDateTime object into String input format.
     *
     * @param date The LocalDateTime object.
     * @return The String with input format.
     */
    protected static String inDate(LocalDateTime date) {
        return date.format(IN_DATE_FORMAT);
    }

    /**
     * Converts LocalDateTime object into String output format.
     *
     * @param date The LocalDateTime object.
     * @return The String with output format.
     */
    protected static String outDate(LocalDateTime date) {
        return date.format(OUT_DATE_FORMAT);
    }
}
