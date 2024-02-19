package chatbot.cortana;

import java.time.LocalDateTime;

/**
 * Utility class to provide helper methods
 */
public class Utils {

    /**
     * Formats the date time string to be in the correct format for LocalDateTime.parse
     * @param timeString the date time string to be formatted
     * @return the formatted date time string
     */
    public static String formatDateTimeString(String timeString) {
        timeString = timeString.trim();
        timeString = timeString.replace("/", "-");
        String[] arr = timeString.split(" ");
        String time = arr[1].trim();
        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);
        return arr[0] + "T" + hour + ":" + minute + ":" + "00";
    }

    /**
     * Parses the date time string to a LocalDateTime object
     * @param timeString the date time string to be parsed
     * @return the LocalDateTime object
     */
    public static LocalDateTime parseDateTimeString(String timeString) {
        return LocalDateTime.parse(formatDateTimeString(timeString));
    }

}
