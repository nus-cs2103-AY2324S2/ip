package Managers;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        "yyyy/MM/dd HHmm",
        "dd MMM yyyy HH:mm"
    };


    // Empty constructor. There is no need to instantiate this class.
    public dateManager() {
    }

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

        throw new IllegalArgumentException("Sorry, I can't recognize that date and time format of yours.");
    }

    /**
     * Convert a date and time into a more reader-friendly one.
     * @param date given date in type of LocalDateTime
     * @return a string representation of the date and time.
     */
    public static String printDate(LocalDateTime date) {
        try {
            return date.format(OUT_FORMATTER);

        } catch (DateTimeException e) {
            return "ERROR";
        }
        
    }

    /**
     * Lists out all accepted date formats.
     */
    public static void validDateFormats() {
        System.out.println("Here's a list of all acceptable date formats:");

        for (String format: DATE_FORMATS) {
            System.out.println(" - " + format);
        }
    }
}
