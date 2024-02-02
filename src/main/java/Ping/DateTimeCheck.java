package Ping;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateTimeCheck {

    public static LocalDate timeCheckOnDate(String s) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(s, formatterDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Check whether it is format dd/MM/yyyy");
            return null;
        }
    }


    public static LocalDateTime timeCheckOnTime(String s) {
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(s, formatterDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Check whether it is format dd/MM/yyyy HHmm");
            return null;
        }
    }

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
