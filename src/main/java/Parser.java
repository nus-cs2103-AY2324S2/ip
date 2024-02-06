import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public static String convertDateTimeToStringUI(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if(localDateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        }

        return localDateTime.format(formatter);
    }
    public static String convertDateTimeToStringStorage(LocalDateTime localDateTime) {
        String formattedDateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return formattedDateTime;
    }
    public static LocalDateTime storageConvertToDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }


    public static LocalDateTime inputConvertToDateTime(String date) throws DateTimeException{
        try {
            String timeFormat = date;
            if (date.contains(":")) {
                int timeIndex = Util.findNthDividerIndex(date, ' ', 1);
                timeFormat = date.substring(0, timeIndex) + "T" + date.substring(timeIndex + 1);
            } else {
                timeFormat = timeFormat + "T00:00";
            }

            System.out.println(timeFormat);
            LocalDateTime localDateTime = LocalDateTime.parse(timeFormat,DateTimeFormatter.ISO_DATE_TIME);
            return localDateTime;
        } catch (DateTimeException e) {
            throw new DateTimeException("enter using the format:/2019-10-15 or /2019-10-15 18:30" );
        }
    }
}

