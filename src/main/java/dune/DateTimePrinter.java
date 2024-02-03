package dune;

import java.time.LocalDateTime;

/**
 * Deals with formatting the date and time.
 */
public class DateTimePrinter {

    /** Months in the year. */
    private String[] months = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    /**
     * Returns a formatted string of the date and time.
     *
     * @param dateTime The date and time to be formatted.
     * @return The formatted date and time.
     */
    public String print(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int year = dateTime.getYear();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        String suffix = "AM";
        if (hour >= 12) {
            suffix = "PM";
            hour -= 12;
        }
        if (hour == 0) {
            hour = 12;
        }
        return day + " " + months[month - 1] + " " + year + " " + hour + ":" + minute + " " + suffix;
    }
}
