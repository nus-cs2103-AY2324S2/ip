package seiki.common;

import java.time.format.DateTimeFormatter;

/**
 * Container for date & time format.
 */
public class DateTime {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final String DATE_TIME_HELPER = "Please use the following format: yyyy/MM/dd HHmm";

}
