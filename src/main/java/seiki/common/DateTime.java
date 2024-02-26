package seiki.common;

import java.time.format.DateTimeFormatter;

/**
 * Container for date & time format.
 */
public class DateTime {
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HHmm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final String DATE_TIME_HELPER = "Please use the following format: " + DATE_TIME_FORMAT;

}
