package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.WilliamException;

/**
 * The DateAndTimeParser class deals with formatting dates and times
 */
public class DateAndTimeParser {
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Check whether the input matches the date and time format
     * 
     * @param input The date and time of the task
     * @throws WilliamException If the format of the date and time is wrong
     */
    public static void acceptDateAndTime(String input) throws WilliamException {
        try {
            LocalDateTime.parse(input, inputFormat);
        } catch (DateTimeParseException e) {
            throw new WilliamException("The date and time format is invalid. Please try again!");
        }
    }

    /**
     * Check whether the '/from' date is before the '/to' date
     * 
     * @param fromDate The input date '/from'
     * @param toDate   The input date '/to'
     * @throws WilliamException If the '/from' date is not before the '/to' date
     */
    public static void checkWhetherToAndFromValid(String fromDate, String toDate) throws WilliamException {
        LocalDateTime fromDateModified = LocalDateTime.parse(fromDate, inputFormat);
        LocalDateTime toDateModified = LocalDateTime.parse(toDate, inputFormat);

        if (fromDateModified.isBefore(toDateModified) == false) {
            throw new WilliamException(
                    "The '/from' date and time should be before '/to' date and time. Please try again!");
        }
    }

    /**
     * Convert String date to LocalDateTime date
     * 
     * @param date Date in String
     * @return Date in LocalDateTime
     */
    public static LocalDateTime convertStringToDate(String date) {
        LocalDateTime modifiedDate = LocalDateTime.parse(date, inputFormat);
        return modifiedDate;
    }
}
