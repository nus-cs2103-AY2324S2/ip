package Utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateTimeUtility {
    // TODO: Add utility to work with the tasklist.txt

    public static LocalDateTime parseStringToLocalDateTime(String rawDateTime) {
        // NOTE: We expect the format to be: "<date> @ <time>"
        String[] rawDateTimeArr = rawDateTime.split("@");
        LocalDate date = LocalDate.parse(rawDateTimeArr[0].strip());;
        LocalTime time = null;

        if (rawDateTimeArr.length > 1) {
            time = LocalTime.parse(rawDateTimeArr[1].strip());
        }
        else {
            time = LocalTime.MIDNIGHT;
        }

        return LocalDateTime.of(date, time);
    }

    public static String parseLocalDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy @ HHmm");

        return dateTime.format(formatter);
    }
}
