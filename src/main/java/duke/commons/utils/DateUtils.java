package duke.commons.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import duke.commons.exceptions.DukeException;

public class DateUtils {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            // Basic ISO date
            DateTimeFormatter.ISO_LOCAL_DATE,
            // ISO Date Time
            DateTimeFormatter.ISO_DATE_TIME,
            // ISO Zoned Date Time
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
            // Custom patterns
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy"),
            // Including time
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"),
            // With time zones
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSXXX"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

    /**
     * Tries to parse any given date string into a LocalDate object using a list of
     * predefined formatters.
     * 
     * @param dateString The date string to parse.
     * @return The parsed LocalDate object, or null if parsing was not successful
     *         with any format.
     */
    public static LocalDate parseDateString(String dateString) throws DukeException {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }

        throw new DukeException("Invalid datetime format.");
    }
}
