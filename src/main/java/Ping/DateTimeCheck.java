package Ping;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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
    public static LocalDate timeCheckOnDate(String s) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(s, formatterDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Check whether it is format dd/MM/yyyy");
            return null;
        }
    }

    /**
     * This method is used to check the format of the time input by the user
     * @param s the time input by the user
     * @return the time in the format of LocalDateTime
     */
    public static LocalDateTime timeCheckOnTime(String s) {
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(s, formatterDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Check whether it is format dd/MM/yyyy HHmm");
            return null;
        }
    }

    /**
     * This method is used to compare the time to ensure that the start time is not greater than the end time
     * @param time1 the start time
     * @param time2 the end time
     * @return true if the start time is not greater than the end time, otherwise return false
     */
    public static boolean timeCompare(LocalDateTime time1, LocalDateTime time2) {
        if (time1.isAfter(time2)) {
            System.out.println("from time cannot larger than to time");
            return false;
        } else if (time1.isEqual(time2)) {
            System.out.println("Two time can not be equal");
            return false;
        } else {
            return true;
        }
    }

}
