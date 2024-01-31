package seedu.banter.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static final String[] USER_INPUT_FORMATS = {"d/M/yyyy HHmm", "dd/MM/yyyy HHmm", "d/M/yyyy", "dd/MM/yyyy"};
    private static final String USER_DISPLAY_FORMAT = "dd MMM yyyy";

    public static LocalDateTime getDateTimeFromUserInput(String str) {
        LocalDateTime parsedDateTime = null;

        for (String format : USER_INPUT_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDateTime = LocalDateTime.parse(str, formatter);
                break;
            } catch (DateTimeParseException ignored) {
                // Do nothing
            }
        }

        if (parsedDateTime == null) {
            System.out.println("Invalid date time format");
        }
        return parsedDateTime;
    }
    
    public static String displayDateTimeToUser(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(USER_DISPLAY_FORMAT);
        return dateTime.format(formatter);
    }
}
