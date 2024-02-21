package oak.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Date time utility.
 */
public class DateTimeUtility {
    /**
     * Parse string to local datetime local date time.
     *
     * @param rawDateTime the raw datetime
     * @return the local datetime
     */
    public static LocalDateTime parseStringToLocalDateTime(String rawDateTime) {
        try {
            return LocalDateTime.parse(rawDateTime);
        } catch (DateTimeParseException e) {  }

        // NOTE: We expect the user-input format to be: "<date> @ <time>"
        String[] rawDateTimeArr = rawDateTime.split("@");
        LocalDate date = LocalDate.parse(rawDateTimeArr[0].strip());;
        LocalTime time = rawDateTimeArr.length > 1 ? LocalTime.parse(rawDateTimeArr[1].strip()) : LocalTime.MIDNIGHT;

        return LocalDateTime.of(date, time);
    }

    /**
     * Parse local datetime to string
     *
     * @param dateTime the datetime
     * @return the parsed local datetime string
     */
    public static String parseLocalDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy @ HHmm");

        return dateTime.format(formatter);
    }
}
