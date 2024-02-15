package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.WilliamException;

/**
 * Deals with formatting dates and times
 */
public class DateAndTimeParser {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Checks whether the input matches the date and time format
     * 
     * @param input The date and time of the task
     * @throws WilliamException If the format of the date and time is wrong
     */
    public static void acceptDateAndTime(String input) throws WilliamException {
        try {
            LocalDateTime.parse(input, INPUT_FORMAT);
            // Assertion to check that the input can be parsed successfully, shows that the format
            // is matched
            assert true;
        } catch (DateTimeParseException e) {
            throw new WilliamException("The date and time format is invalid. Please try again!");
        }
    }

    /**
     * Checks whether the '/from' date is before the '/to' date
     * 
     * @param fromDate The input date '/from'
     * @param toDate The input date '/to'
     * @throws WilliamException If the '/from' date is not before the '/to' date
     */
    public static void checkWhetherToAndFromValid(String fromDate, String toDate)
            throws WilliamException {
        LocalDateTime fromDateModified = LocalDateTime.parse(fromDate, INPUT_FORMAT);
        LocalDateTime toDateModified = LocalDateTime.parse(toDate, INPUT_FORMAT);

        if (!fromDateModified.isBefore(toDateModified)) {
            throw new WilliamException(
                    "The '/from' date and time should be before '/to' date and time. Please try again!");
        }

        // Assertion to check that fromDateModified is before toDateModified
        assert fromDateModified.isBefore(toDateModified) : "From date is before to date.";
    }

    /**
     * Returns a String date that is converted to LocalDateTime date
     * 
     * @param date Date in String
     * @return Date in LocalDateTime
     */
    public static LocalDateTime convertStringToDate(String date) {
        LocalDateTime modifiedDate = LocalDateTime.parse(date, INPUT_FORMAT);

        // Assertion to check that the conversion result is not null
        assert modifiedDate != null : "Date conversion results in not a null object.";

        return modifiedDate;
    }
}
