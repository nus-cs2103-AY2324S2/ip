package someboty.Managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import someboty.Exceptions.InputException;

/**
 * dateManager handles processing and formatting of date and time.
 * This class does not need to be initialized to use its methods and so
 * does not have a constructor.
 */
public class DateManager {

    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

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

    /**
     * Parses a given date into a LocalDateTime object.
     * @param date Given string date and time
     * @return a LocalDateTime object containing the given date.
     * @throws InputException Given date is not in an acceptable format.
     */
    public static LocalDateTime parseDate(String date) throws InputException {
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
     * Convert a date and time into a more reader-friendly format.
     * @param date A LocalDateTime object to format.
     * @return String representation of the date and time.
     */
    public static String printDate(LocalDateTime date) {
        return date.format(OUT_FORMAT);
    }

    /**
     * Lists out all accepted date and time formats.
     * @return String representation of the list of accepted date and time formats.
     */
    public static String PrintDateFormats() {
        String response = "Here's a list of all acceptable date formats:\n";

        for (String format: DATE_FORMATS) {
            response = response + " - " + format + "\n";
        }

        return response;
    }
}
