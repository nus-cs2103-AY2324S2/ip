package rochin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    /**
     * Process the date string from input file and convert it to a LocalDate object.
     *
     * @param dateTimeString The datetime string.
     * @return The LocalDate object.
     * @throws RochinException If the date string is in a wrong format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws RochinException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (Exception e) {
            throw new RochinException("OOPS!!! Invalid date/time format. Please use yyyy-MM-dd HHmm format.");
        }
    }


    /**
     * Formats a LocalDate object and convert it to a string.
     *
     * @param datetime A LocalDate object to be converted.
     * @return The datetime string.
     */
    public static String printDate(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
