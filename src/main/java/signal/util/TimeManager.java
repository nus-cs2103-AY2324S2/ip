package signal.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * Manages the conversion of date and time inputs from the user.
 */
public class TimeManager {
    public TimeManager() {
    }

    /**
     * Converts the date into a string to be displayed to the user.
     *
     * @param date The LocalDate recorded in the Task object.
     * @return The date in dd MMM yyyy format.
     */
    public String formatDate(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }

    /**
     * Converts the date from a string input by the user to be parsed into a LocalDate.
     *
     * @param date The date string from the user.
     * @return The LocalDate parsed.
     */
    public LocalDate convertDate(String date) {
        char[] splitDate = date.toCharArray();
        StringBuilder converted = new StringBuilder();
        if (splitDate.length == 10) {
            converted.append(splitDate[6]);
            converted.append(splitDate[7]);
            converted.append(splitDate[8]);
            converted.append(splitDate[9]);
        } else {
            String yr = String.valueOf(LocalDate.now().getYear());
            converted.append(yr);
        }

        converted.append('-');
        converted.append(splitDate[3]);
        converted.append(splitDate[4]);
        converted.append('-');
        converted.append(splitDate[0]);
        converted.append(splitDate[1]);
        return LocalDate.parse(converted);
    }

    /**
     * Converts the time into a string to be displayed to the user.
     *
     * @param time The LocalTime recorded in the Task object.
     * @return The time in h:mm a format.
     */
    public String formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }

    /**
     * Converts the time from a string input by the user to be parsed into a LocalTime.
     *
     * @param twentyFourHrTime The date string from the user.
     * @return The LocalTime parsed.
     */
    public LocalTime convertTime(String twentyFourHrTime) {
        char[] splitTime = twentyFourHrTime.toCharArray();
        StringBuilder converted = new StringBuilder();
        converted.append(splitTime[0]);
        converted.append(splitTime[1]);
        converted.append(':');
        converted.append(splitTime[2]);
        converted.append(splitTime[3]);
        converted.append(":00");
        return LocalTime.parse(converted);
    }

}
