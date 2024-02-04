package someboty.Managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import someboty.Exceptions.InputException;

/**
 * A class to handle processing and formatting of dates and time.
 */
public class dateManager {

    /* --- VARIABLES --- */
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    // accepted date formats
    private static final String[] DATE_FORMATS = {
        "yyyy-MM-dd HHmm",
        "dd-MM-yyyy HHmm",
        "MM-dd-yyyy HHmm",
        "dd/MM/yyyy HHmm",
        "MM/dd/yyyy HHmm",
        "yyyy/MM/dd HHmm",
        "dd MMM yyyy HH:mm"
    };

    /* --- METHODS --- */

    /**
     * Parses a given date.
     * @param date given date in type of string
     * @return a LocalDateTime containing the date and time of the given date
     */
    public static LocalDateTime parseDate(String date) {
        if (date.split(" ").length == 1) {
            date = date + " 2359";
        } 

        for (String format : DATE_FORMATS) {
            try {
                return LocalDateTime.parse(
                    date,
                    DateTimeFormatter.ofPattern(format)
                    );

            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new InputException("Sorry, I can't recognize that date and time format of yours.");
    }

    /**
     * Convert a date and time into a more reader-friendly one.
     * @param date given date in type of LocalDateTime
     * @return a string representation of the date and time.
     */
    public static String printDate(LocalDateTime date) {
        return date.format(OUT_FORMATTER);
    }

    /**
     * Lists out all accepted date formats.
     */
    public static String validDateFormats() {
        String response = "Here's a list of all acceptable date formats:\n";

        for (String format: DATE_FORMATS) {
            response = response + " - " + format + "\n";
        }

        return response;
    }
}
