package seedu.banter.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a date time utility class.
 */
public class DateTime {
    private static final String[] USER_INPUT_FORMATS = {"d/M/yyyy HHmm", "dd/MM/yyyy HHmm", "d/M/yyyy", "dd/MM/yyyy"};
    private static final String USER_DISPLAY_FORMAT = "dd MMM yyyy HHmm";

    /**
     * Parses a string into a LocalDateTime object.
     * @param str String to be parsed
     * @return LocalDateTime object
     */
    public static LocalDateTime getDateTimeFromUserInput(String str) {
        LocalDateTime parsedDateTime = null;

        for (String format : USER_INPUT_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDateTime = LocalDateTime.parse(str, formatter);
                break;
            } catch (DateTimeParseException ignored) {
                // TODO: handle exception
            }
        }

        if (parsedDateTime == null) {
            System.out.println("Invalid date time format");
        }
        return parsedDateTime;
    }

    /**
     * Converts a LocalDateTime object into a string for display to the user.
     * @param dateTime LocalDateTime object
     * @return String representation of the LocalDateTime object
     */
    public static String displayDateTimeToUser(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(USER_DISPLAY_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Returns the string representation of accepted date time formats for user input.
     * @return String representation of accepted date time formats for user input.
     */
    public static String getAcceptedDateTimeFormats() {
        StringBuilder acceptedFormats = new StringBuilder();
        for (String format : USER_INPUT_FORMATS) {
            if (acceptedFormats.length() > 0) {
                acceptedFormats.append(", ");
            }
            acceptedFormats.append(format);
        }
        return acceptedFormats.toString();
    }
}
