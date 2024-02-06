import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Parser {
    public static boolean isDateTime(String date) {
        String time = date.substring(date.length()-4);
        if (time.contains(":")) {
            return true;
        } else {
            return false;
        }
    }
    public static String convertToDateOnly(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return localDate.format(formatter);
        } catch (DateTimeException e) {
            System.out.println("enter using the format:/2019-10-15");
            System.out.println(e.getMessage());
        }
        return null; // convert to propagating the error in the future
    }

    public static String convertToDateTime(String date) {
        try {
            int timeIndex = Util.findNthDividerIndex(date, ' ',1);
            String timeFormat = date.substring(0,timeIndex) + "T" + date.substring(timeIndex+1);
            LocalDateTime localDateTime = LocalDateTime.parse(timeFormat,DateTimeFormatter.ISO_DATE_TIME);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return localDateTime.format(formatter);
        } catch (DateTimeException e) {
            System.out.println("enter using the format:/2019-10-15");
        }
        return null;
    }
}

