package ping.timematch;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ping.exceptions.PingException;


/**
 * This class is used to check the format of the date and time input by the user
 * and compare the time to ensure that the start time is not greater than the end time
 *
 */
public class DateTimeCheck {

    /**
     * This method is used to check the format of the date input by the user
     * @param s the date input by the user
     * @return the date in the format of LocalDate
     */
    public static LocalDate timeCheckOnDate(String s) throws PingException {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(s, formatterDate);
        } catch (DateTimeParseException e) {
            throw new PingException("Invalid date format. Check whether it is format /by dd/MM/yyyy");
        }
    }

    /**
     * This method is used to check the format of the time input by the user
     * @param s the time input by the user
     * @return the time in the format of LocalDateTime
     */
    public static LocalDateTime timeCheckOnTime(String s) throws PingException {
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(s, formatterDateTime);
        } catch (DateTimeParseException e) {
            throw new PingException("Invalid time format. Check whether it is format dd/MM/yyyy HHmm");
        }
    }

    /**
     * This method is used to compare the time to ensure that the start time is not greater than the end time
     * @param time1 the start time
     * @param time2 the end time
     * @return true if the start time is not greater than the end time, otherwise return false
     */
    public static boolean timeCompare(LocalDateTime time1, LocalDateTime time2) throws PingException {
        if (time1.isAfter(time2)) {
            throw new PingException("Start time cannot be greater than end time");
        }
        return true;
    }

}
