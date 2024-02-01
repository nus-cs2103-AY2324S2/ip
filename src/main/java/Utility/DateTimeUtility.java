package Utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTimeUtility {
    // TODO: Add utility to work with the tasklist.txt
    public static LocalDateTime parseStringToLocalDateTime(String rawDateTime) {
        // FIX: How do we separate the formats of the tasklist better?
        try {
            return LocalDateTime.parse(rawDateTime);
        }
        catch (DateTimeParseException e) {}

        // NOTE: We expect the user-input format to be: "<date> @ <time>"
        String[] rawDateTimeArr = rawDateTime.split("@");
        LocalDate date = LocalDate.parse(rawDateTimeArr[0].strip());;
        LocalTime time = rawDateTimeArr.length > 1 ? LocalTime.parse(rawDateTimeArr[1].strip()) : LocalTime.MIDNIGHT;

        return LocalDateTime.of(date, time);
    }

    public static String parseLocalDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy @ HHmm");

        return dateTime.format(formatter);
    }
}
