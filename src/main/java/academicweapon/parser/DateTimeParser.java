package academicweapon.parser;

import academicweapon.exceptions.DukeExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for parsing and formatting date-time in the Duke application
 */
public class DateTimeParser {
    /**
     * Private constructor to prevent instantiation of the DateTimeParser utility class.
     */
    private DateTimeParser() {

    }

    /**
     * Parses the input string to create a LocalDateTime object.
     *
     * @param by The string containing date and time to be parsed
     * @return LocalDateTime object representing the parsed date and time
     * @throws DukeExceptions If the input string is not in the expected format
     */
    public static LocalDateTime parseDateTime(String by) throws DukeExceptions {
        String[] splitBy = by.split(" ");

        if (splitBy.length != 2) {
            throw new DukeExceptions("Please include date and time, in this format, yyyy-mm-dd and hhmm.");
        }

        String[] splitDayMonthYear = new String[3];
        int day = 0;
        int month = 0;
        int year = 0;
        if (splitBy[0].contains("/")) {
            splitDayMonthYear = splitBy[0].split("/");
        } else if (splitBy[0].contains("-")) {
            splitDayMonthYear = splitBy[0].split("-");
        } else if (splitBy[1].contains("/")) {
            splitDayMonthYear = splitBy[1].split("/");
        } else if (splitBy[1].contains("-")) {
            splitDayMonthYear = splitBy[1].split("-");
        } else {
            throw new DukeExceptions("Please include date and time, in thi format, yyyy-mm-dd hhmm");
        }

        month = Integer.parseInt(splitDayMonthYear[1]);

        if (splitDayMonthYear[0].length() <= 2) {
            day = Integer.parseInt(splitDayMonthYear[0]);
            year = Integer.parseInt(splitDayMonthYear[2]);
        } else {
            day = Integer.parseInt(splitDayMonthYear[2]);
            year = Integer.parseInt(splitDayMonthYear[0]);
        }

        int hour = Integer.parseInt(splitBy[1]) / 100;
        int min = Integer.parseInt(splitBy[1]) % 100;

        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, min);
        return ldt;
    }

    /**
     * Formats the provided LocalDateTime object as a string.
     *
     * @param ldt The LocalDateTime object to be formatted
     * @return Formatted string representing the date and the time
     */
    public static String toString(LocalDateTime ldt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");
        return ldt.format(dtf);
    }
}
